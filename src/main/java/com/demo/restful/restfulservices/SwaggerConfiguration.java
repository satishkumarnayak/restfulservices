package com.demo.restful.restfulservices;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Satish Nayak", "http://www.instanceofcake.com",
			"satish.nayak@instanceofcake.com.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("InstanceOfCake Title ", "InstanceOfCake Documentation",
			"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList());
	private static final Set<String> DEFAULT_PRODUCES_CONSUMERS = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_CONSUMERS)
				.consumes(DEFAULT_PRODUCES_CONSUMERS);
	}
}
