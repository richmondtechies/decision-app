package com.techtalks.decision.app.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Created by awg049 on 11/22/16.
 */
@Immutable
@JsonDeserialize
public final class DecisionAppInputMessage {

    public final String applicationId;
    public final String name;
    public final String income;
    public final String ackId;



    @JsonCreator
    public DecisionAppInputMessage(String applicationId, String sorId, String accountId, String income, String ackId ) {
        this.applicationId = Preconditions.checkNotNull(applicationId, "ApplicationId should not be null");
        this.name = Preconditions.checkNotNull(applicationId, "name  should not be null");
        this.income = Preconditions.checkNotNull(applicationId, "income id should not be null");
        this.ackId = ackId;

    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof DecisionAppInputMessage && equalTo((DecisionAppInputMessage) another);
    }

    private boolean equalTo(DecisionAppInputMessage another) {
        return applicationId.equals(another.applicationId) &&
                name.equals(another.name) && income.equals(another.income)
                ;
    }

    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + applicationId.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}