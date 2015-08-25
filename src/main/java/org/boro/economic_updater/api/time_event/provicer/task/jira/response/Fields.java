package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

    private String summary;

    @JsonProperty("customfield_10800")
    private Project project;

    public Fields() {
    }

    public Fields(String summary, Project project) {
        this.summary = summary;
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "summary='" + summary + '\'' +
                ", project=" + project +
                '}';
    }
}
