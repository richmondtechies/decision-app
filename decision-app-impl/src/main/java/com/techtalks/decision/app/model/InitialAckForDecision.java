package com.techtalks.decision.app.model;

import akka.actor.ActorRef;

/**
 * Created by tki214 on 4/11/17.
 */
public class InitialAckForDecision {
    String ackId;
    ActorRef replyTo;
    DecisionOutputMsg decisionOutputMsg;

    public InitialAckForDecision(String ackId, ActorRef replyTo, DecisionOutputMsg decisionOutputMsg) {
        this.ackId = ackId;
        this.replyTo = replyTo;
        this.decisionOutputMsg = decisionOutputMsg;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public ActorRef getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ActorRef replyTo) {
        this.replyTo = replyTo;
    }

    public DecisionOutputMsg getDecisionOutputMsg() {
        return decisionOutputMsg;
    }

    public void setDecisionOutputMsg(DecisionOutputMsg decisionOutputMsg) {
        this.decisionOutputMsg = decisionOutputMsg;
    }
}
