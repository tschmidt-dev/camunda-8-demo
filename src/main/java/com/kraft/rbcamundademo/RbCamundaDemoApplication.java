package com.kraft.rbcamundademo;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath:*.bpmn")
public class RbCamundaDemoApplication {
	public static void main(String[] args) {
		System.out.println(
				ZonedDateTime
						.now(ZoneOffset.UTC)
						.plus(15, ChronoUnit.SECONDS)
						.truncatedTo(ChronoUnit.SECONDS)
						.format(DateTimeFormatter.ISO_DATE_TIME)
		);
		SpringApplication.run(RbCamundaDemoApplication.class, args);
	}
}
