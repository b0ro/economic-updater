package org.boro.economic_updater.api.time_event.provicer.task.jira.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by artur.borodziej on 18.08.15.
 */
public class WorklogEntry {

    private long id;

    private String comment;

    private long timeSpent;

    private String author;

    private String updateAuthor;

    @JsonProperty("created")
    private Date createDate;

    @JsonProperty("updated")
    private Date updateDate;

    private Date startDate;

    public WorklogEntry() {
    }

    public WorklogEntry(long id, String comment, long timeSpent, String author, String updateAuthor, Date createDate,
                        Date updateDate, Date startDate) {
        this.id = id;
        this.comment = comment;
        this.timeSpent = timeSpent;
        this.author = author;
        this.updateAuthor = updateAuthor;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public String getAuthor() {
        return author;
    }

    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "WorklogEntry{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", timeSpent=" + timeSpent +
                ", author='" + author + '\'' +
                ", updateAuthor='" + updateAuthor + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", startDate=" + startDate +
                '}';
    }
}