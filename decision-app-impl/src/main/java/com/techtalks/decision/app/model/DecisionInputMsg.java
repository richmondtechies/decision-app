package com.techtalks.decision.app.model;

/**
 * Created by tki214 on 3/10/17.
 */
public class DecisionInputMsg {


    CreditScoreResponse creditScoreResponse;
    VerificationStatusResponse verificationStatusResponse;

    public DecisionInputMsg(CreditScoreResponse creditScoreResponse, VerificationStatusResponse verificationStatusResponse) {
        this.creditScoreResponse = creditScoreResponse;
        this.verificationStatusResponse = verificationStatusResponse;
    }


    public CreditScoreResponse getCreditScoreResponse() {
        return creditScoreResponse;
    }

    public void setCreditScoreResponse(CreditScoreResponse creditScoreResponse) {
        this.creditScoreResponse = creditScoreResponse;
    }

    public VerificationStatusResponse getVerificationStatusResponse() {
        return verificationStatusResponse;
    }

    public void setVerificationStatusResponse(VerificationStatusResponse verificationStatusResponse) {
        this.verificationStatusResponse = verificationStatusResponse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecisionInputMsg that = (DecisionInputMsg) o;

        if (!creditScoreResponse.equals(that.creditScoreResponse)) return false;
        return verificationStatusResponse.equals(that.verificationStatusResponse);

    }

    @Override
    public int hashCode() {
        int result = creditScoreResponse.hashCode();
        result = 31 * result + verificationStatusResponse.hashCode();
        return result;
    }
}
