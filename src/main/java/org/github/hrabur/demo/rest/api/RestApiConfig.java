package org.github.hrabur.demo.rest.api;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackageClasses = RestApiConfig.class)
public class RestApiConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter(
			Jackson2ObjectMapperBuilder.json()
				// Tells dates to be serialized in ISO-8601 format (2015-07-09T19:00+03:00)
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.build()));
	}

	@Bean
	public Docket apiDocs() {
		return new Docket(DocumentationType.SWAGGER_2)
			.pathMapping("/api")
			.apiInfo(apiInfo())
			.directModelSubstitute(ZonedDateTime.class, Date.class);
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Events API")
			.description("Events management REST API")
			.contact("Nikolay Petkov")
			.version("0.1")
			.build();

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry
			.addResourceHandler("webjars/springfox-swagger-ui/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}
}
