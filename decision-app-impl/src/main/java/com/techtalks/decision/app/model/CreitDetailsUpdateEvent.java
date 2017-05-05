package com.techtalks.decision.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;
import com.techtalks.decision.app.api.DecisionAppInputMessage;

import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 11/22/16.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class CreitDetailsUpdateEvent implements Jsonable{

    final public CreditScoreResponse creditScoreResponse;
    @JsonCreator
    public CreitDetailsUpdateEvent(CreditScoreResponse creditScoreResponse) {
        this.creditScoreResponse = creditScoreResponse;
    }

    @Override
    public String toString() {
        return "CreitDetailsUpdateEvent{" +
                "creditScoreResponse=" + creditScoreResponse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreitDetailsUpdateEvent)) return false;

        CreitDetailsUpdateEvent that = (CreitDetailsUpdateEvent) o;

        return creditScoreResponse != null ? creditScoreResponse.equals(that.creditScoreResponse) : that.creditScoreResponse == null;

    }

    @Override
    public int hashCode() {
        return creditScoreResponse != null ? creditScoreResponse.hashCode() : 0;
    }
}
