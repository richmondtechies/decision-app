package com.techtalks.decision.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 11/22/16.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class DecisionUpdateEvent implements Jsonable{

    final public DecisionOutputMsg decisionOutputMsg;
    @JsonCreator
    public DecisionUpdateEvent(DecisionOutputMsg decisionOutputMsg) {
        this.decisionOutputMsg = decisionOutputMsg;
    }

    @Override
    public String toString() {
        return "DecisionUpdateEvent{" +
                "decisionOutputMsg=" + decisionOutputMsg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DecisionUpdateEvent)) return false;

        DecisionUpdateEvent that = (DecisionUpdateEvent) o;

        return decisionOutputMsg != null ? decisionOutputMsg.equals(that.decisionOutputMsg) : that.decisionOutputMsg == null;

    }

    @Override
    public int hashCode() {
        return decisionOutputMsg != null ? decisionOutputMsg.hashCode() : 0;
    }
}
