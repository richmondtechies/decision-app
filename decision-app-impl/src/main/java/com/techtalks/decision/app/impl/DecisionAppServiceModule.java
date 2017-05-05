package com.techtalks.decision.app.impl;

import com.techtalks.decision.app.api.DecisionAPIService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Created by tki214 on 11/22/16.
 */
public class DecisionAppServiceModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(DecisionAPIService.class, DecisionAPIServiceImpl.class));
    }
}
