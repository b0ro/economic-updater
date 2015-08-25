package org.boro.economic_updater.api.response;

import java.util.Date;

/**
 * Created by b0ro on 13.08.15.
 */
public interface TimeEvent {

    long getId();

    String getTitle();

    String getDescription();

    String getOrigin();

    String getUri();

    float getDuration();

    Date getDate();

    long getProjectId();

    long getActivityId();
}
