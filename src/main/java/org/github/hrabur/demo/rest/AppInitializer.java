package org.github.hrabur.demo.rest;

import org.github.hrabur.demo.rest.api.RestApiConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// For simplicity there is no application configuration
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { RestApiConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/rest/*" };
	}

}
