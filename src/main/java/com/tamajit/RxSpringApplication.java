package com.tamajit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RxSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxSpringApplication.class, args);
	}
	
	@Bean
	public Scheduler employeeScheculer(){
		ExecutorService poolA = Executors.newFixedThreadPool(100);
		return Schedulers.from(poolA);
	}
	
	@Bean
	public Docket docket() {
		Predicate<String> paths = PathSelectors.ant("/**");

		ApiInfo apiInfo = new ApiInfoBuilder().title("Welcome")
				.description("The Spring Boot sample project")
				.contact("guharoytamajit@gmail.com").version("1.0.0").build();

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo).select().paths(paths).build();

		return docket;
	}
}
