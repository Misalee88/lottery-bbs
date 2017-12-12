package com.zhegu.lottery.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class })
public class BbsSystemManagementApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BbsSystemManagementApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("===========SpringApplicationBuilder==============================");
		return application.sources(BbsSystemManagementApplication.class);
	}
}
