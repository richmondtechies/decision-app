package com.techtalks.decision.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;

/**
 * Created by awg049 on 11/22/16.
 */
@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class VerficationDetailUpdateEvent implements Jsonable{

    final public VerificationStatusResponse verificationStatusResponse;
    @JsonCreator
    public VerficationDetailUpdateEvent(VerificationStatusResponse verificationStatusResponse) {
        this.verificationStatusResponse = verificationStatusResponse;
    }

    @Override
    public String toString() {
        return "VerficationDetailUpdateEvent{" +
                "creditScoreResponse=" + verificationStatusResponse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerficationDetailUpdateEvent)) return false;

        VerficationDetailUpdateEvent that = (VerficationDetailUpdateEvent) o;

        return verificationStatusResponse != null ? verificationStatusResponse.equals(that.verificationStatusResponse) : that.verificationStatusResponse == null;

    }

    @Override
    public int hashCode() {
        return verificationStatusResponse != null ? verificationStatusResponse.hashCode() : 0;
    }
}
