package org.boro.economic_updater.api.time_event.provicer.task.jira;

import org.boro.economic_updater.api.response.TimeEvent;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


public class JiraTaskProviderTest {

    @Test
    public void testGetCurrentlyAssignedTasksReturnsCorrectResponse() throws Exception {
        JiraTaskProvider taskProvider = new JiraTaskProvider(
                "username", "password", "http://jira-host.com", createCurrentlyAssignedTasksRestTemplate());
        List<TimeEvent> tasks = taskProvider.getCurrentlyAssignedTasks();
        assertThat(tasks.size(), is(1));

        TimeEvent task = tasks.get(0);
        assertThat(task.getId(), is(49624L));
        assertThat(task.getTitle(), is("PNG image handling"));
        assertThat(task.getOrigin(), is("currently_assigned_tasks"));
        assertThat(task.getUri(), is("http://jira-host.com/browse/MM-2409"));

        //@TODO should be filled from worklog
//        assertThat(task.getDuration(), is(0.0F));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        assertThat(formatter.format(task.getDate()), is(formatter.format(new Date())));
        assertThat(task.getProjectId(), is(44018L));

        //@TODO should be 40 taken from worklog
//        assertThat(task.getActivityId(), is(40));
    }

    @Test
    public void testGetTasksFromWorklog() throws Exception {

    }

    @Test
    public void testGetTasksFromActivityStream() throws Exception {

    }

    private RestTemplate createCurrentlyAssignedTasksRestTemplate() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        String uri = "http://jira-host.com/rest/api/2/search?jql=assignee%3Dusername%20and%20status%20not%20in(Open," +
                "Blocked,Resolved)%20and%20sprint%20in%20openSprints()&fields=key,summary,worklog,customfield_10800";

        mockServer.expect(requestTo(uri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getResponseFromFile("currently-assigned-tasks-response.json"),
                        MediaType.APPLICATION_JSON));

        return restTemplate;
    }

    private String getResponseFromFile(String fileName) {
        StringBuilder result = new StringBuilder("");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}