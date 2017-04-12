package com.techtalks.decision.app.model;

/**
 * Created by tki214 on 3/10/17.
 */
public class DecisionOutputMsg {

    String decisionValue;

    public DecisionOutputMsg(String decisionValue) {
        this.decisionValue = decisionValue;
    }

    public String getDecisionValue() {
        return decisionValue;
    }

    public void setDecisionValue(String decisionValue) {
        this.decisionValue = decisionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecisionOutputMsg that = (DecisionOutputMsg) o;

        return decisionValue.equals(that.decisionValue);

    }

    @Override
    public int hashCode() {
        return decisionValue.hashCode();
    }

    @Override
    public String toString() {
        return "DecisionOutputMsg{" +
                "decisionValue='" + decisionValue + '\'' +
                '}';
    }
}
