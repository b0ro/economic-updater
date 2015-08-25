package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timesheet {

    @JsonProperty("worklog")
    private List<Worklog> worklogs;

    private Date startDate;

    private Date endDate;

    public Timesheet() {
    }

    public Timesheet(List<Worklog> worklogs, Date startDate, Date endDate) {
        this.worklogs = worklogs;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Worklog> getWorklogs() {
        return worklogs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "worklogs=" + worklogs +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
