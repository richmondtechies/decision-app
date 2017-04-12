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
public class CreditScoreResponse implements Jsonable {
    int creditScore;


    public CreditScoreResponse(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditScoreResponse that = (CreditScoreResponse) o;

        return creditScore == that.creditScore;

    }

    @Override
    public int hashCode() {
        return creditScore;
    }
}
