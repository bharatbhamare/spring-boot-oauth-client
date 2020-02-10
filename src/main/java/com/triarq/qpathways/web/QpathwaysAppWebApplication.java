package com.triarq.qpathways.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication

public class QpathwaysAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(QpathwaysAppWebApplication.class, args);
	}

}
