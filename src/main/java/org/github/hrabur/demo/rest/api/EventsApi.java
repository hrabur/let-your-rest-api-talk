package org.github.hrabur.demo.rest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/events")
public class EventsApi {
	
	private static final Event REST_API_MEET_UP;
	private static final List<Registration> REST_API_MEET_UP_REGISTRATIONS;
	
	static {
		REST_API_MEET_UP = new Event();
		REST_API_MEET_UP.setId("let-your-rest-api-talk");
		REST_API_MEET_UP.setTitle("Let your REST API talk");
		REST_API_MEET_UP.setDescription("Live demo how to generate documentation with Swagger and JSONDoc");
		REST_API_MEET_UP.setStart(ZonedDateTime.parse("2015-07-09T19:00+03:00"));
		REST_API_MEET_UP.setEnd(ZonedDateTime.parse("2015-07-09T21:00+03:00"));
		
		Registration registration = new Registration();
		registration.setId(String.valueOf(1));
		registration.setEmail("npetkov@proxiad.com");
		registration.setName("Nikolay Petkov");
		REST_API_MEET_UP_REGISTRATIONS = new ArrayList<>();
		REST_API_MEET_UP_REGISTRATIONS.add(registration);
	}

	@ApiOperation("List upcoming events")
	@RequestMapping(
		value="/upcoming",
		method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Event>> listUpcomingEvents() {
		return ResponseEntity.ok(Arrays.asList(REST_API_MEET_UP));
	}
	
	@ApiOperation("Show event details")
	@RequestMapping(
		value="/{eventId}",
		method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showEventDetails(@PathVariable String eventId) {
		if (!REST_API_MEET_UP.getId().equals(eventId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(Arrays.asList(REST_API_MEET_UP));
	}
	
	@ApiOperation("Show event registrations")
	@RequestMapping(
		value="/{eventId}/registrations",
		method = RequestMethod.GET,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showEventRegistrations(@PathVariable String eventId) {
		if (!REST_API_MEET_UP.getId().equals(eventId)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(Arrays.asList(REST_API_MEET_UP_REGISTRATIONS));
	}
	
	@ApiOperation("Register for event")
	@RequestMapping(
		value="/{eventId}/registrations",
		method = RequestMethod.POST,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerForEvent(
			@PathVariable String eventId,
			@RequestBody Registration registration) {
		if (!REST_API_MEET_UP.getId().equals(eventId)) {
			return ResponseEntity.notFound().build();
		}

		REST_API_MEET_UP_REGISTRATIONS.add(registration);
		return ResponseEntity.ok(registration);
	}
	
	@ApiOperation("Cancel registration")
	@RequestMapping(
		value="/{eventId}/registrations/{registrationId}",
		method = RequestMethod.DELETE,
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerForEvent(
			@PathVariable String eventId,
			@PathVariable String registrationId) {
		if (!REST_API_MEET_UP.getId().equals(eventId)) {
			return ResponseEntity.notFound().build();
		}
		
		Iterator<Registration> registrations = REST_API_MEET_UP_REGISTRATIONS.iterator();
		while (registrations.hasNext()) {
			Registration registration = registrations.next();
			if (registration.getId().equals(registrationId)) {
				registrations.remove();
				return ResponseEntity.ok().build();
			}
		}
		
		return ResponseEntity.notFound().build();
	}
}
