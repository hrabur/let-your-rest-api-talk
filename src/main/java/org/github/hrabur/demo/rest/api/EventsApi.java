package org.github.hrabur.demo.rest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/events")
public class EventsApi {

	@RequestMapping(
		value="/upcoming",
		method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Event>> listUpcomingEvents() {
		Event restApiMeetUp = new Event();
		restApiMeetUp.setTitle("Let your REST API talk");
		restApiMeetUp.setDescription("Live demo how to generate documentation with Swagger and JSONDoc");
		restApiMeetUp.setStart(ZonedDateTime.parse("2015-07-09T19:00+03:00"));
		restApiMeetUp.setEnd(ZonedDateTime.parse("2015-07-09T21:00+03:00"));
		return ResponseEntity.ok(Arrays.asList(restApiMeetUp));
	}
}
