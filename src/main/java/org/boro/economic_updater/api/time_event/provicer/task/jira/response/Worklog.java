package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worklog {

    private String key;

    private String summary;

    List<WorklogEntry> entries;

    public Worklog() {
    }

    public Worklog(String key, String summary, List<WorklogEntry> entries) {
        this.key = key;
        this.summary = summary;
        this.entries = entries;
    }

    public String getKey() {
        return key;
    }

    public String getSummary() {
        return summary;
    }

    public List<WorklogEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Worklog{" +
                "key='" + key + '\'' +
                ", summary='" + summary + '\'' +
                ", entries=" + entries +
                '}';
    }
}