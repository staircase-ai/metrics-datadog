package org.staircase.metrics.datadog;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class TagUtilsTest {

    @Test
    public void mergeTagsWithoutDupKey() {
        List<String> tags1 = new ArrayList<>();
        tags1.add("key1:v1");
        tags1.add("key2:v2");
        List<String> tags2 = new ArrayList<>();
        tags2.add("key3:v3");
        tags2.add("key4:v4");


        List<String> expected = new ArrayList<>();
        expected.addAll(tags1);
        expected.addAll(tags2);
        assert (new TreeSet<>(TagUtils.mergeTags(tags1, tags2)).equals(
                new TreeSet<>(expected)));
    }

    @Test
    public void mergeTagsWithDupKey() {
        List<String> tags1 = new ArrayList<>();
        tags1.add("key1:v1");
        tags1.add("key2:v2");
        List<String> tags2 = new ArrayList<>();
        tags2.add("key2:v3");
        tags2.add("key4:v4");


        List<String> expected = new ArrayList<>();
        expected.add("key1:v1");
        expected.addAll(tags2);
        assert (new TreeSet<>(TagUtils.mergeTags(tags1, tags2)).equals(
                new TreeSet<>(expected)));
    }
}
