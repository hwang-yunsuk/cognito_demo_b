package com.example.cognito_demo_b.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/siteB")
    public String login(Authentication authentication) {
        // 認証済みの場合は /siteB にリダイレクト
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/siteB";
        }
        // 未認証の場合は siteB ページを表示
        return "siteB";
    }
}