package org.github.hrabur.demo.rest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestApiConfig.class)
@WebAppConfiguration
public class EventsApiTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testListUpcomingEvents() throws Exception {
		mockMvc
			.perform(get("/events/upcoming").accept(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(content().json(
				"[{" + 
				"	title=\"Let your REST API talk\"," +
				"	description=\"Live demo how to generate documentation with Swagger and JSONDoc\"," +
				// "	start=\"2015-07-09T19:00:00\"," +
				// "	end=\"2015-07-09T21:00:00\"" +
				"}]"));
	}
}
