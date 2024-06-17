package io.dropwizard.metrics.common;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatadogReporterFactoryTest {
  @Test
  public void isDiscoverable() throws Exception {
    var subtypes = new DiscoverableSubtypeResolver().getDiscoveredSubtypes();
    assertTrue(subtypes.contains(DatadogReporterFactory.class));
  }
}