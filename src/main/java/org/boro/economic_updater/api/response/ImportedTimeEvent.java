package org.boro.economic_updater.api.response;

import java.util.Date;

public class ImportedTimeEvent implements TimeEvent {

    private long id;

    private String title;

    private String description;

    private String origin;

    private String uri;

    private float duration;

    private Date date;

    private long projectId;

    private long activityId;

    public ImportedTimeEvent() {
    }

    public ImportedTimeEvent(long id, String title, String origin, String uri, Date date, long projectId) {
        this.id = id;
        this.title = title;
        this.origin = origin;
        this.uri = uri;
        this.date = date;
        this.projectId = projectId;
    }

    public ImportedTimeEvent(long id, String title, String description, String origin, String uri, float duration,
                             Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.origin = origin;
        this.uri = uri;
        this.duration = duration;
        this.date = date;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public long getProjectId() {
        return projectId;
    }

    @Override
    public long getActivityId() {
        return activityId;
    }

    @Override
    public String toString() {
        return "ImportedTimeEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                ", uri='" + uri + '\'' +
                ", duration=" + duration +
                ", date=" + date +
                ", projectId=" + projectId +
                ", activityId=" + activityId +
                '}';
    }
}