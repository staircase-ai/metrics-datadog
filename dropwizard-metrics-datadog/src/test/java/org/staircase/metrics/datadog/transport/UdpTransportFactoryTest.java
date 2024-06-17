package org.staircase.metrics.datadog.transport;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UdpTransportFactoryTest {
    @Test
    public void isDiscoverable() {
        var subtypes = new DiscoverableSubtypeResolver().getDiscoveredSubtypes();
        assertTrue(subtypes.contains(UdpTransportFactory.class));
    }
}