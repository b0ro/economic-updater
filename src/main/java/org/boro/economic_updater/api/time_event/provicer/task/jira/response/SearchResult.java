package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.boro.economic_updater.api.response.TimeEvent;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    private long startAt;

    private long maxResults;

    private long total;

    List<Issue> issues;

    public SearchResult() {
    }

    public SearchResult(long startAt, long maxResults, long total, List<Issue> issues) {
        this.startAt = startAt;
        this.maxResults = maxResults;
        this.total = total;
        this.issues = issues;
    }

    public long getStartAt() {
        return startAt;
    }

    public long getMaxResults() {
        return maxResults;
    }

    public long getTotal() {
        return total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "startAt=" + startAt +
                ", maxResults=" + maxResults +
                ", total=" + total +
                ", issues=" + issues +
                '}';
    }
}
