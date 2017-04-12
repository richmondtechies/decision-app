package com.techtalks.decision.app.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 3/10/17.
 */

@Immutable
@JsonDeserialize
public class AckInputMsg {
    private String ackId;


    public AckInputMsg(String ackId) {
        this.ackId = ackId;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AckInputMsg that = (AckInputMsg) o;

        return ackId.equals(that.ackId);

    }

    @Override
    public int hashCode() {
        return ackId.hashCode();
    }


    @Override
    public String toString() {
        return "AckInputMsg{" +
                "ackId='" + ackId + '\'' +
                '}';
    }
}
