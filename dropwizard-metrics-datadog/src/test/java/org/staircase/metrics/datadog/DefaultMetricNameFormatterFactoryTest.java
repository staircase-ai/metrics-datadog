package org.staircase.metrics.datadog;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultMetricNameFormatterFactoryTest {
  @Test
  public void isDiscoverable() {
    var subtypes = new DiscoverableSubtypeResolver().getDiscoveredSubtypes();
    assertTrue(subtypes.contains(DefaultMetricNameFormatterFactory.class));
  }

  @Test
  public void testBuild() {
    var factory = new DefaultMetricNameFormatterFactory().build();
    assertEquals(DefaultMetricNameFormatter.class, factory.getClass());
  }
}
