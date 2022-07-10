package org.staircase.metrics.datadog;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface DynamicTagsCallbackFactory extends Discoverable {
  DynamicTagsCallback build();
}
