package org.github.hrabur.demo.rest.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class DemoApi {

	@RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("It works");
	}
}
