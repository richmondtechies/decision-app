package com.techtalks.decision.app.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Created by tki214 on 11/22/16.
 */
@Immutable
@JsonDeserialize
public final class DecisionAppInputMessage {

    public final String name;
    public final String ssn;
    public final String income;
    public final String dob;



    @JsonCreator
    public DecisionAppInputMessage(String name, String ssn, String income, String dob) {
        this.name = Preconditions.checkNotNull(name, "ApplicationId should not be null");
        this.ssn = Preconditions.checkNotNull(ssn, "name  should not be null");
        this.income = Preconditions.checkNotNull(income, "income id should not be null");
        this.dob = Preconditions.checkNotNull(dob, "income id should not be null");

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecisionAppInputMessage that = (DecisionAppInputMessage) o;

        if (!name.equals(that.name)) return false;
        if (!ssn.equals(that.ssn)) return false;
        if (!income.equals(that.income)) return false;
        return dob.equals(that.dob);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + ssn.hashCode();
        result = 31 * result + income.hashCode();
        result = 31 * result + dob.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DecisionAppInputMessage{" +
                "name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", income='" + income + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}