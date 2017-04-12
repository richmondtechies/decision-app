package com.techtalks.decision.app.impl;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.techtalks.decision.app.model.DecisionInputMsg;
import com.techtalks.decision.app.model.DecisionOutputMsg;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by tki214 on 3/7/17.
 */

public class FinalizeDecisionActor extends AbstractActor {


    public FinalizeDecisionActor() {
        receive(initialReceive());
    }
    private PartialFunction<Object, BoxedUnit> initialReceive() {
        return ReceiveBuilder.match(DecisionInputMsg.class, this::makeDecision)
                .build();
    }

    private void makeDecision(DecisionInputMsg msg){
            System.out.println("FinalizeDecisionActor:"+msg.getVerificationStatusResponse().getVerficationStatus());
            DecisionOutputMsg decisionOutputMsg = null;
            if(msg.getVerificationStatusResponse().getVerficationStatus().equals("unverified")){
                decisionOutputMsg = new DecisionOutputMsg("Declined");
            }else{
                decisionOutputMsg = new DecisionOutputMsg("Approved");
            }
            sender().tell(decisionOutputMsg, self());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }


    public static Props props() {
        return Props.create(FinalizeDecisionActor.class, () -> new FinalizeDecisionActor());
    }

}
