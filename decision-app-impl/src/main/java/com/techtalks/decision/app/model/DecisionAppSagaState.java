package com.techtalks.decision.app.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 11/22/16.
 */

@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public class DecisionAppSagaState implements Jsonable {

    String stateDescription;
    CreditScoreResponse creditScoreResponse;
    VerificationStatusResponse verificationStatusResponse;
    DecisionOutputMsg decisionOutputMsg;


    public DecisionAppSagaState(String stateDescription, CreditScoreResponse creditScoreResponse, VerificationStatusResponse verificationStatusResponse,DecisionOutputMsg decisionOutputMsg) {
        this.stateDescription = stateDescription;
        this.creditScoreResponse = creditScoreResponse;
        this.verificationStatusResponse = verificationStatusResponse;
        this.decisionOutputMsg = decisionOutputMsg;

    }

    public void updateCreditScoreResponse(CreditScoreResponse creditScoreResponse){
        this.creditScoreResponse = creditScoreResponse;
    }


    public void updateVerificationStatusResponse(VerificationStatusResponse verificationStatusResponse){
        this.verificationStatusResponse = verificationStatusResponse;
    }

    public void updateDecisionOutputMsg(DecisionOutputMsg decisionOutputMsg){
        this.decisionOutputMsg = decisionOutputMsg;
    }

    public CreditScoreResponse getCreditScoreResponse() {
        return creditScoreResponse;
    }


    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public VerificationStatusResponse getVerificationStatusResponse() {
        return verificationStatusResponse;
    }

    public DecisionOutputMsg getDecisionOutputMsg() {
        return decisionOutputMsg;
    }

    public void setDecisionOutputMsg(DecisionOutputMsg decisionOutputMsg) {
        this.decisionOutputMsg = decisionOutputMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecisionAppSagaState that = (DecisionAppSagaState) o;

        if (!stateDescription.equals(that.stateDescription)) return false;
        if (!creditScoreResponse.equals(that.creditScoreResponse)) return false;
        if (!verificationStatusResponse.equals(that.verificationStatusResponse)) return false;
        return decisionOutputMsg.equals(that.decisionOutputMsg);

    }

    @Override
    public int hashCode() {
        int result = stateDescription.hashCode();
        result = 31 * result + creditScoreResponse.hashCode();
        result = 31 * result + verificationStatusResponse.hashCode();
        result = 31 * result + decisionOutputMsg.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DecisionAppSagaState{" +
                "stateDescription='" + stateDescription + '\'' +
                ", creditScoreResponse=" + creditScoreResponse +
                ", verificationStatusResponse=" + verificationStatusResponse +
                ", decisionOutputMsg=" + decisionOutputMsg +
                '}';
    }
}
