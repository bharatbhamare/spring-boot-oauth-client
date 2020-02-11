package com.triarq.qpathways.web.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AcsessToken {

	public static String getAcsessToken() {
		OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder
				.getContext().getAuthentication().getDetails();
		return oAuth2AuthenticationDetails.getTokenType().concat(" ")
				.concat(oAuth2AuthenticationDetails.getTokenValue());

	}
}
