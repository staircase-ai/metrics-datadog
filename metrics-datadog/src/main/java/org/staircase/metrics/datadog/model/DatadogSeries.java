package org.staircase.metrics.datadog.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.staircase.metrics.datadog.TaggedName;

import java.util.ArrayList;
import java.util.List;

public abstract class DatadogSeries<T extends Number> {
  abstract protected String getType();

  private final String name;
  private final T count;
  private final Long epoch;
  private final String host;
  private final List<String> tags;

  public DatadogSeries(String name, T count, Long epoch, String host, List<String> additionalTags) {
    TaggedName taggedName = TaggedName.decode(name);
    this.name = taggedName.getMetricName();
    this.tags = taggedName.getEncodedTags();

    if (additionalTags != null) {
      this.tags.addAll(additionalTags);
    }
    this.count = count;
    this.epoch = epoch;
    this.host = host;
  }

  @JsonInclude(Include.NON_NULL)
  public String getHost() {
    return host;
  }

  public String getMetric() {
    return name;
  }

  public List<String> getTags() {
    return tags;
  }

  public List<List<Number>> getPoints() {
    List<Number> point = new ArrayList<>();
    point.add(epoch);
    point.add(count);

    List<List<Number>> points = new ArrayList<>();
    points.add(point);
    return points;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DatadogSeries)) return false;

    DatadogSeries that = (DatadogSeries) o;

    if (!count.equals(that.count)) return false;
    if (!epoch.equals(that.epoch)) return false;
    if (!host.equals(that.host)) return false;
    if (!name.equals(that.name)) return false;
    return tags.equals(that.tags);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + count.hashCode();
    result = 31 * result + epoch.hashCode();
    result = 31 * result + host.hashCode();
    result = 31 * result + tags.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "DatadogSeries{" +
        "name='" + name + '\'' +
        ", count=" + count +
        ", epoch=" + epoch +
        ", host='" + host + '\'' +
        ", tags=" + tags +
        '}';
  }
}
