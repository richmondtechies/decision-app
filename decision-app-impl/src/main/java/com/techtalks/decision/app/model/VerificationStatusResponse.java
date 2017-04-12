package com.techtalks.decision.app.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 3/10/17.
 */

@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class VerificationStatusResponse implements Jsonable {
    private String verficationStatus;


    public VerificationStatusResponse(String verficationStatus) {
        this.verficationStatus = verficationStatus;
    }

    public String getVerficationStatus() {
        return verficationStatus;
    }

    public void setVerficationStatus(String verficationStatus) {
        this.verficationStatus = verficationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationStatusResponse that = (VerificationStatusResponse) o;

        return verficationStatus.equals(that.verficationStatus);

    }

    @Override
    public int hashCode() {
        return verficationStatus.hashCode();
    }
}
