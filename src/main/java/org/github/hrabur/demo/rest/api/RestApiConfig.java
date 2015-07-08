package org.github.hrabur.demo.rest.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackageClasses = RestApiConfig.class)
public class RestApiConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket apiDocs() {
		return new Docket(DocumentationType.SWAGGER_2);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry
			.addResourceHandler("/webjars/springfox-swagger-ui/")
			.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
}
