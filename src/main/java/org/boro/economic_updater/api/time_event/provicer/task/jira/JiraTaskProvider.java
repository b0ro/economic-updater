package org.boro.economic_updater.api.time_event.provicer.task.jira;

import org.apache.commons.codec.binary.Base64;
import org.boro.economic_updater.api.response.ImportedTimeEvent;
import org.boro.economic_updater.api.response.TimeEvent;
import org.boro.economic_updater.api.time_event.provicer.task.jira.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Component used to fetch details about user's Jira tasks
 */
public class JiraTaskProvider {

    private static final String REST_CURRENTLY_ASSIGNED_ISSUES_URI_FORMAT =
            "/rest/api/2/search?jql=assignee=%s+and+status+not+in(Open,Blocked,Resolved)+and+sprint+in+openSprints()" +
            "&fields=key,summary,worklog,customfield_10800";

    private static final String BROWSE_ISSUE_URI_FORMAT = "/browse/%s";

    private static final String REST_TIMESHEET_URI_FORMAT =
            "/rest/timesheet-gadget/1.0/raw-timesheet.json?startDate=%s&endDate=%s";

    private static final Logger logger = LoggerFactory.getLogger(JiraTaskProvider.class);
    RestTemplate template;
    HttpEntity<String> authenticationRequest;
    private String username;
    private String password;
    private String host;

    public JiraTaskProvider() {
    }

    public JiraTaskProvider(String username, String password, String host, RestTemplate template) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.template = template;

        createAuthenticationRequest();
    }

    /*public List<EconomicEntry> getTasksByDate(Date date) throws IOException {
        List<EconomicEntry> entries = new ArrayList<>();


        return entries;
    }*/

    /**
     * Returns list of user's currently assigned tasks
     *
     * @return
     * @throws UnsupportedEncodingException
     * @TODO I really don't like this exception here, think about some mapping
     */
    public List<TimeEvent> getCurrentlyAssignedTasks() throws UnsupportedEncodingException {
        List<TimeEvent> list = new ArrayList<>();

        String uri = String.format(host + REST_CURRENTLY_ASSIGNED_ISSUES_URI_FORMAT, username);
        uri = URLDecoder.decode(uri, "UTF-8");
        logger.info("Requesting {}", uri);

        ResponseEntity<SearchResult> response = template.exchange(
                uri, HttpMethod.GET, authenticationRequest, SearchResult.class);

        List<Issue> issues = response.getBody().getIssues();
        for (Issue issue : issues) {
            list.add(issueAsTimeEvent(issue));
        }
        return list;
    }

    /**
     * Returns tasks from user's worklog
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws UnsupportedEncodingException
     * @TODO I really don't like this exception here, think about some mapping
     */
    public List<TimeEvent> getTasksFromWorklog(Date startDate, Date endDate) throws UnsupportedEncodingException {
        List<TimeEvent> list = new ArrayList<>();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        String uri = String.format(host + REST_TIMESHEET_URI_FORMAT, dateFormatter.format(startDate),
                dateFormatter.format(endDate));
        uri = URLDecoder.decode(uri, "UTF-8");
        logger.info("Requesting {}", uri);

        ResponseEntity<Timesheet> response = template.exchange(
                uri, HttpMethod.GET, authenticationRequest, Timesheet.class);

        List<Worklog> worklogs = response.getBody().getWorklogs();
        for (Worklog worklog : worklogs) {
            list.add(worklogAsTimeEvent(worklog));
        }
        return list;
    }

    public List<TimeEvent> getTasksFromActivityStream() {
        List<TimeEvent> result = new ArrayList<>();
        return result;
    }

    /**
     * Creates request with headers needed for basic authentication
     */
    private void createAuthenticationRequest() {
        String credentials = username + ":" + password;
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials);
        HttpEntity<String> request = new HttpEntity<>(headers);

        this.authenticationRequest = request;
    }

    /**
     * Maps Jira Issue response object to TimeEvent object
     *
     * @param issue
     * @return
     */
    private TimeEvent issueAsTimeEvent(Issue issue) {
        String previewUrl = String.format(host + BROWSE_ISSUE_URI_FORMAT, issue.getKey());

        String projectName = issue.getFields().getProject().getName();
        long projectId = projectName.contains("-") ? Long.parseLong(projectName.split("-")[0]) : 0L;

        //@TODO check if this date is correct
        TimeEvent event = new ImportedTimeEvent(
                Long.parseLong(issue.getId()), issue.getFields().getSummary(), "currently_assigned_tasks",
                previewUrl, new Date(), projectId);

        return event;
    }

    /**
     * Maps Jira Worklog response object to Timeevent object
     *
     * @param worklog
     * @return
     */
    private TimeEvent worklogAsTimeEvent(Worklog worklog) {
        String previewUrl = String.format(host + BROWSE_ISSUE_URI_FORMAT, worklog.getKey());

        //@TODO check if date is correct
        TimeEvent ev = new ImportedTimeEvent(
                0, worklog.getSummary(), "worklog_entry", previewUrl, new Date(), 0);

        List<TimeEvent> events = new ArrayList<>();
        for (WorklogEntry entry : worklog.getEntries()) {
            //@TODO id info is missing
            TimeEvent event = new ImportedTimeEvent(
                    0, worklog.getSummary(), "worklog_entry", previewUrl, new Date(), 0);
        }

        return ev;
    }
}