package org.staircase.metrics.datadog.transport;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import org.fest.assertions.api.Assertions;
import org.junit.Test;

public class UdpTransportFactoryTest {
    @Test
    public void isDiscoverable() {
        Assertions
                .assertThat(new DiscoverableSubtypeResolver().getDiscoveredSubtypes())
                .contains(UdpTransportFactory.class);
    }
}