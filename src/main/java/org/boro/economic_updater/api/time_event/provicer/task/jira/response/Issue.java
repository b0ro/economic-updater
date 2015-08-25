package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.boro.economic_updater.api.response.ImportedTimeEvent;
import org.boro.economic_updater.api.response.TimeEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    String id;

    String self;

    String key;

    Fields fields;

    public Issue() {
    }

    public Issue(String id, String self, String key, Fields fields) {
        this.id = id;
        this.self = self;
        this.key = key;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public String getSelf() {
        return self;
    }

    public String getKey() {
        return key;
    }

    public Fields getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", self='" + self + '\'' +
                ", key='" + key + '\'' +
                ", fields=" + fields +
                '}';
    }
}
