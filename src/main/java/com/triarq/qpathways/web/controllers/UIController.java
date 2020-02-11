package com.triarq.qpathways.web.controllers;

import java.util.List;

import org.bouncycastle.asn1.x509.sigi.PersonalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.triarq.qpathways.web.config.AcsessToken;
import com.triarq.qpathways.web.models.Patients;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {

	@Autowired
	RestTemplate restTemplate;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
	}

	@GetMapping("/")
	public String welcomePage() {
		return "home";
	}

	@GetMapping("/secure")
	public String securePage() {
		return "secure";
	}

	@GetMapping("/patients")
	public String getPatients(Model model) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AcsessToken.getAcsessToken());
		HttpEntity<Patients> patientEntity = new HttpEntity<Patients>(httpHeaders);
		try {
			ResponseEntity<Patients[]> responseEntity = restTemplate.exchange("http://localhost:8083/patients/",
					HttpMethod.GET, patientEntity, Patients[].class);
			model.addAttribute("patients", responseEntity.getBody());
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getMessage());
			ResponseEntity res = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
					.body(e.getResponseBodyAsString());
			model.addAttribute("error", res);
		}

		return "secure";

	}

}
