package com.techtalks.decision.app.impl;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.techtalks.decision.app.model.CreditScoreResponse;
import com.techtalks.decision.app.model.IndividualDetailMsg;


import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Created by tki214 on 3/7/17.
 */

public class RetrieveBureauDetailsActor extends AbstractActor {


    public RetrieveBureauDetailsActor() {
        receive(initialReceive());
    }
    private PartialFunction<Object, BoxedUnit> initialReceive() {
        return ReceiveBuilder.match(IndividualDetailMsg.class, this::processMsgFromProcessTransfer)
                .build();
    }

    private void processMsgFromProcessTransfer(IndividualDetailMsg msg){
        int lower = 300;
        int upper = 850;
        int randomNumber = (int) (Math.random() * (upper - lower)) + lower;

        if(msg.getSocial().equals("0")){
            throw new RuntimeException("Test Actor Supervision..");
        }

        CreditScoreResponse msgForParent = new CreditScoreResponse(randomNumber);
        sender().tell(msgForParent, self());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }


    public static Props props() {
        return Props.create(RetrieveBureauDetailsActor.class, () -> new RetrieveBureauDetailsActor());
    }

}
