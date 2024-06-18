package org.staircase.metrics.datadog.transport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UdpTransportTest {
  private static final String REACHABLE_HOST = "127.0.0.1";
  private static final String UNREACHABLE_HOST = "unreachable";

  @Test
  public void constructsWithReachableHost() {
    final UdpTransport transport = new UdpTransport.Builder().withStatsdHost(REACHABLE_HOST).build();
    assertNotNull(transport);
  }

  @Test
  public void constructsWhenUnreachableHostWithRetry() {
    assertThrows(RuntimeException.class, () -> new UdpTransport.Builder().withStatsdHost(UNREACHABLE_HOST).withRetryingLookup(true).build());
  }

  @Test
  public void throwsWhenUnreachableHost() {
    assertThrows(RuntimeException.class, () -> new UdpTransport.Builder().withStatsdHost(UNREACHABLE_HOST).build());
  }

  @Test
  public void volatileResolverResolvesByTheTimeTheHostIsResolvable() throws Exception {
    final int port = 1111;
    try {
      UdpTransport.volatileAddressResolver(UNREACHABLE_HOST, port).call();
      fail();
    } catch (final Exception ignored) {}
    // ^ This should throw because the host is unresolvable.

    assertNotNull(UdpTransport.volatileAddressResolver(REACHABLE_HOST, port).call()); // Returns with resolved by the time it's resolvable.
  }
}
