package org.staircase.metrics.serializer;

import org.staircase.metrics.datadog.model.DatadogCounter;
import org.staircase.metrics.datadog.model.DatadogGauge;

import java.io.IOException;

/**
 * This defines the interface to build a datadog request body.
 * The call order is expected to be:
 *   startObject() -> One or more of appendGauge/appendCounter -> endObject()
 * Note that this is a single-use class and nothing can be appended once endObject() is called.
 */
public interface Serializer {

  /**
   * Write starting marker of the datadog time series object
   */
  void startObject() throws IOException;

  /**
   * Append a gauge to the time series
   */
  void appendGauge(DatadogGauge gauge) throws IOException;

  /**
   * Append a counter to the time series
   */
  void appendCounter(DatadogCounter counter) throws IOException;

  /**
   * Mark ending of the datadog time series object
   */
  void endObject() throws IOException;

  /**
   * Get datadog time series object serialized as a string
   */
  String getAsString() throws IOException;
}
