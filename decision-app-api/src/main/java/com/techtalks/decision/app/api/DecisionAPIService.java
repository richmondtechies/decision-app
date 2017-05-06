/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.techtalks.decision.app.api;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * The hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the HelloService.
 */
public interface DecisionAPIService extends Service {

  /**
   * Example: curl http://localhost:9000/api/hello/Alice
   */
  ServiceCall<DecisionAppInputMessage, String> processRequest();

  ServiceCall<AckInputMsg, String> getDecision();

  /**
   * Example: curl -H "Content-Type: application/json" -X POST -d '{"message":
   * "Hi"}' http://localhost:9000/api/hello/Alice
   */

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("updateAISRegistry").withCalls(
        pathCall("/api/card/application",  this::processRequest),
        pathCall("/api/card/decision",  this::getDecision)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
