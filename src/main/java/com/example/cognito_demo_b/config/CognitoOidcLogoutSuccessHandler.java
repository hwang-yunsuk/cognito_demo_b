package com.example.cognito_demo_b.config;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;

@Component
public class CognitoOidcLogoutSuccessHandler implements LogoutSuccessHandler {

    private final String clientId = "1ic6cvkuk1j3qekuephv925odg";
    private final String logoutUrl = "https://web02.auth.us-east-1.amazoncognito.com/logout";
    private final String redirectUriToSiteA = "http://localhost:8080/siteA";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String url = UriComponentsBuilder.fromHttpUrl(logoutUrl)
                .queryParam("client_id", clientId)
                .queryParam("logout_uri", redirectUriToSiteA)
                .build().toUriString();
        response.sendRedirect(url);
    }
}
