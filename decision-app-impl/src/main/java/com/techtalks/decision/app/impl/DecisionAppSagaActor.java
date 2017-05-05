package com.techtalks.decision.app.impl;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.persistence.AbstractPersistentActor;
import com.techtalks.decision.app.api.AckInputMsg;
import com.techtalks.decision.app.api.DecisionAppInputMessage;
import com.techtalks.decision.app.model.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.PartialFunction;
import scala.compat.java8.FutureConverters;
import scala.runtime.BoxedUnit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static akka.pattern.Patterns.pipe;

/**
 * Created by tki214 on 11/22/16.
 */
public class DecisionAppSagaActor extends AbstractPersistentActor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionAppSagaActor.class);

   private static ExecutorService service = Executors.newCachedThreadPool();
    private DecisionAppSagaState state = new DecisionAppSagaState("Not yet Started", null, null, null);
    final Config config = ConfigFactory.load();
    ActorRef clientRef = null;
    String ackId = null;

    public DecisionAppSagaActor(String ackId) {
        this.ackId = ackId;
    }

    @Override
    public String persistenceId() {
        return ackId;
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveRecover() {
        return ReceiveBuilder.match(StartedUpdateEvent.class,
                startedUpdateEvent -> {
                    this.state = new DecisionAppSagaState("Started replaying", null, null, null);
                    System.out.println("startedUpdateEvent:"+startedUpdateEvent);
                }).match(CreitDetailsUpdateEvent.class,
                creitDetailsUpdateEvent -> {
                    this.state = new DecisionAppSagaState(this.state.getStateDescription(), creitDetailsUpdateEvent.creditScoreResponse, this.state.getVerificationStatusResponse(), this.state.getDecisionOutputMsg());
                    System.out.println("creitDetailsUpdateEvent:"+creitDetailsUpdateEvent);
                }).match(VerficationDetailUpdateEvent.class,
                verficationDetailUpdateEvent -> {
                    this.state = new DecisionAppSagaState(this.state.getStateDescription(), this.state.getCreditScoreResponse(), verficationDetailUpdateEvent.verificationStatusResponse, this.state.getDecisionOutputMsg());
                    System.out.println("verficationDetailUpdateEvent:"+verficationDetailUpdateEvent);
                }).match(DecisionUpdateEvent.class,
                    decisionUpdateEvent -> {
                    this.state = new DecisionAppSagaState(this.state.getStateDescription(), this.state.getCreditScoreResponse(), this.state.getVerificationStatusResponse(), decisionUpdateEvent.decisionOutputMsg);
                    System.out.println("verficationDetailUpdateEvent:"+decisionUpdateEvent);
                }).build();
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveCommand() {

        return ReceiveBuilder.match(DecisionAppInputMessage.class,
                startUpdate -> {
                    clientRef = sender();
                    StartedUpdateEvent event = new StartedUpdateEvent(startUpdate);
                    persist(event, (e) -> {
                        this.state = new DecisionAppSagaState("Calling IDM", null, null, null);

                        IndividualDetailMsg individualDetailMsg = new IndividualDetailMsg("sample", "sample","sample");

                        ActorRef creditScoreActor = context().actorOf(RetrieveBureauDetailsActor.props());
                        creditScoreActor.tell(individualDetailMsg, self());

                        ActorRef verificationStatusActor = context().actorOf(RetrieveVerificationStatusActor.props());
                        verificationStatusActor.tell(individualDetailMsg, self());
                        context().system().eventStream().publish(e);

                    });
                }).match(AckInputMsg.class, ackInputMsg->{
                    clientRef = sender();
                    CompletableFuture<InitialAckForDecision> initialRequestAckCompletableFuture = CompletableFuture.completedFuture( new InitialAckForDecision(ackId, clientRef, this.state.getDecisionOutputMsg()));
                    pipe(FutureConverters.toScala(initialRequestAckCompletableFuture), context().dispatcher()).to(self());

                }).match(CreditScoreResponse.class, creditScoreResponse->{
                    CreitDetailsUpdateEvent creitDetailsUpdateEvent = new CreitDetailsUpdateEvent(creditScoreResponse);
                    persist(creitDetailsUpdateEvent, (e) -> {
                        this.state.updateCreditScoreResponse(creditScoreResponse);
                        if (this.state.getCreditScoreResponse() != null && this.state.getVerificationStatusResponse() != null) {
                            DecisionInputMsg finalizeDecisionMsg = new DecisionInputMsg(this.state.getCreditScoreResponse(), this.state.getVerificationStatusResponse());
                            ActorRef finalizeDecisionActor = context().actorOf(FinalizeDecisionActor.props());
                            finalizeDecisionActor.tell(finalizeDecisionMsg, self());
                        }
                        context().system().eventStream().publish(e);
                    });
                }).match(VerificationStatusResponse.class, verificationStatusResponse->{
                    VerficationDetailUpdateEvent verficationDetailUpdateEvent = new VerficationDetailUpdateEvent(verificationStatusResponse);
                    persist(verficationDetailUpdateEvent, (e) -> {
                        this.state.updateVerificationStatusResponse(verificationStatusResponse);
                        if (this.state.getCreditScoreResponse() != null && this.state.getVerificationStatusResponse() != null) {
                            DecisionInputMsg finalizeDecisionMsg = new DecisionInputMsg(this.state.getCreditScoreResponse(), this.state.getVerificationStatusResponse());
                            ActorRef finalizeDecisionActor = context().actorOf(FinalizeDecisionActor.props());
                            finalizeDecisionActor.tell(finalizeDecisionMsg, self());
                        }
                        context().system().eventStream().publish(e);

                    });
                }).match(DecisionOutputMsg.class, decisionOutputMsg->{
                    DecisionUpdateEvent decisionUpdateEvent = new DecisionUpdateEvent(decisionOutputMsg);
                    System.out.println("Decision from FinalizeDecisonActor===>" + decisionOutputMsg.getDecisionValue());
                    persist(decisionUpdateEvent, (e) -> {
                        this.state.updateDecisionOutputMsg(decisionOutputMsg);
                        System.out.println("Decision from FinalizeDecisonActor===>" + decisionOutputMsg.getDecisionValue());
                        //clientRef.tell(decisionOutputMsg.getDecisionValue()+","+ackId,self());
                        context().system().eventStream().publish(e);
                        saveSnapshot(this.state);
                    });
                    clientRef.tell(decisionOutputMsg.getDecisionValue()+","+ackId,self());
                    getContext().stop(self());

            }).match(InitialAckForDecision.class, initialAckForDecision-> {
                System.out.println("************::::::::"+initialAckForDecision.getDecisionOutputMsg().getDecisionValue());
                initialAckForDecision.getReplyTo().tell(initialAckForDecision.getDecisionOutputMsg().getDecisionValue(),self());
                getContext().stop(self());
            }).build();


    }


    public static Props props(String ackId) {
        return Props.create(DecisionAppSagaActor.class, () -> new DecisionAppSagaActor(ackId));
    }


}
