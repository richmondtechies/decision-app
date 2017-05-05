package com.techtalks.decision.app.model;

import com.techtalks.decision.app.api.DecisionAppInputMessage;
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
public class StartedUpdateEvent implements Jsonable{

    final public DecisionAppInputMessage decisionAppInputMessage;
    @JsonCreator
    public StartedUpdateEvent(DecisionAppInputMessage decisionAppInputMessage) {
        this.decisionAppInputMessage = decisionAppInputMessage;
    }

    @Override
    public String toString() {
        return "StartedUpdateEvent{" +
                "decisionAppInputMessage=" + decisionAppInputMessage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartedUpdateEvent)) return false;

        StartedUpdateEvent that = (StartedUpdateEvent) o;

        return decisionAppInputMessage != null ? decisionAppInputMessage.equals(that.decisionAppInputMessage) : that.decisionAppInputMessage == null;

    }

    @Override
    public int hashCode() {
        return decisionAppInputMessage != null ? decisionAppInputMessage.hashCode() : 0;
    }
}
