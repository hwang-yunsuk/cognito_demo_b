package com.example.cognito_demo_b.config;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class CognitoOidcLogoutSuccessHandler implements LogoutSuccessHandler {

    // .envファイルから環境変数を読み込むためのDotenvインスタンスを作成
    private final Dotenv dotenv = Dotenv.load();
    // クライアントID
    private final String clientId = dotenv.get("CLIENT_ID_SITE_B");
    // ログアウトURL
    private final String logoutUrl = dotenv.get("LOGOUT_URL_SITE_B");
    // サイトAへのリダイレクトURI
    private final String redirectUriToSiteA = dotenv.get("REDIRECT_URI_TO_SITE_A");

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // ログアウトURLにクエリパラメータを追加
        String url = UriComponentsBuilder.fromHttpUrl(logoutUrl)
                .queryParam("client_id", clientId)
                .queryParam("logout_uri", redirectUriToSiteA)
                .build().toUriString();
        // ログアウトURLにリダイレクト
        response.sendRedirect(url);
    }
}