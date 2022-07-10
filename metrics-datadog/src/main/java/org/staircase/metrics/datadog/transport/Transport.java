package org.staircase.metrics.datadog.transport;

import org.staircase.metrics.datadog.model.DatadogCounter;
import org.staircase.metrics.datadog.model.DatadogGauge;

import java.io.Closeable;
import java.io.IOException;

/**
 * The transport layer for pushing metrics to datadog
 */
public interface Transport extends Closeable {

  /**
   * Build a request context.
   */
  Request prepare() throws IOException;

  /**
   * A request for batching of metrics to be pushed to datadog.
   * The call order is expected to be:
   *    one or more of addGauge, addCounter -> send()
   */
  interface Request {

    /**
     * Add a gauge
     */
    void addGauge(DatadogGauge gauge) throws IOException;

    /**
     * Add a counter to the request
     */
    void addCounter(DatadogCounter counter) throws IOException;

    /**
     * Send the request to datadog
     */
    void send() throws Exception;
  }
}
