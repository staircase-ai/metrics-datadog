package io.dropwizard.metrics.common;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.staircase.metrics.datadog.DatadogReporter;
import org.staircase.metrics.datadog.DefaultMetricNameFormatterFactory;
import org.staircase.metrics.datadog.DynamicTagsCallbackFactory;
import org.staircase.metrics.datadog.MetricNameFormatterFactory;
import org.staircase.metrics.datadog.transport.AbstractTransportFactory;

import java.util.EnumSet;
import java.util.List;

import static org.staircase.metrics.datadog.DatadogReporter.Expansion;

@JsonTypeName("datadog")
public class DatadogReporterFactory extends BaseReporterFactory {

  @JsonProperty
  private String host = null;

  @JsonProperty
  private List<String> tags = null;

  @Valid
  @JsonProperty
  private DynamicTagsCallbackFactory dynamicTagsCallback = null;

  @JsonProperty
  private String prefix = null;

  @Valid
  @NotNull
  @JsonProperty
  private EnumSet<Expansion> expansions = EnumSet.allOf(Expansion.class);

  @Valid
  @NotNull
  @JsonProperty
  private MetricNameFormatterFactory metricNameFormatter = new DefaultMetricNameFormatterFactory();

  @Valid
  @NotNull
  @JsonProperty
  private AbstractTransportFactory transport = null;

  public ScheduledReporter build(MetricRegistry registry) {
    return DatadogReporter.forRegistry(registry)
        .withTransport(transport.build())
        .withHost(host)
        .withTags(tags)
        .withPrefix(prefix)
        .withExpansions(expansions)
        .withMetricNameFormatter(metricNameFormatter.build())
        .withDynamicTagCallback(dynamicTagsCallback != null ? dynamicTagsCallback.build() : null)
        .filter(getFilter())
        .convertDurationsTo(getDurationUnit())
        .convertRatesTo(getRateUnit())
        .build();
    }
}