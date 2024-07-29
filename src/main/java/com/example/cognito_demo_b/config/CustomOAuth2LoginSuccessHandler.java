package com.example.cognito_demo_b.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    // トークンレスポンスクライアント
    private final DefaultAuthorizationCodeTokenResponseClient tokenResponseClient;

    public CustomOAuth2LoginSuccessHandler() {
        tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        OAuth2AuthorizationCodeGrantRequestEntityConverter requestEntityConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();
        tokenResponseClient.setRequestEntityConverter(requestEntityConverter);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 認証成功後に /siteB にリダイレクト
        response.sendRedirect("/siteB");
    }
}