package com.example.cognito_demo_b.controller;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasicController {

    private final JwtDecoder jwtDecoder;

    public BasicController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/siteB")
    public String protectedPage(@RequestParam(name = "token") String token, Model model) {
        Jwt jwt = jwtDecoder.decode(token);
        model.addAttribute("username", jwt.getClaim("username"));
        return "siteB";  // siteB.htmlに対応するテンプレート
    }
}
