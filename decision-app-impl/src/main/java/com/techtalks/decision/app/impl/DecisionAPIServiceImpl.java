package com.techtalks.decision.app.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import com.techtalks.decision.app.api.AckInputMsg;
import com.techtalks.decision.app.api.DecisionAPIService;
import com.techtalks.decision.app.api.DecisionAppInputMessage;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import play.api.inject.Injector;
import scala.compat.java8.FutureConverters;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

/**
 * Created by tki214 on 11/22/16.
 */
public class DecisionAPIServiceImpl  implements DecisionAPIService {
    private final ActorSystem actorSystem;


private Injector injector;

    @Inject
    public DecisionAPIServiceImpl(Injector injector,ActorSystem actorSystem) {

        this.actorSystem = actorSystem;
        this.injector = injector;
    }

    @Override
    public ServiceCall<DecisionAppInputMessage, String> processRequest() {
        final Timeout timeout = new Timeout( Duration.create(10, TimeUnit.SECONDS));
        return request -> {
            String ackId = UUID.randomUUID().toString();
            ActorRef ref = actorSystem
                    .actorOf(Props.create(DecisionAppSagaActor.class, () -> new DecisionAppSagaActor(ackId)));
            CompletionStage<Object> res = FutureConverters.toJava(ask(ref, request,timeout  ));

           return res.thenApply(x -> x.toString());


        };
    }


    @Override
    public ServiceCall<AckInputMsg, String> getDecision() {
        final Timeout timeout = new Timeout( Duration.create(1, TimeUnit.SECONDS));
        return request -> {
            System.out.println("IMPL---->"+request.getAckId());
            ActorRef ref = actorSystem
                    .actorOf(Props.create(DecisionAppSagaActor.class, () -> new DecisionAppSagaActor(request.getAckId())));
            CompletionStage<Object> res = FutureConverters.toJava(ask(ref, request,timeout  ));

            return res.thenApply(x -> x.toString());


        };
    }
}
