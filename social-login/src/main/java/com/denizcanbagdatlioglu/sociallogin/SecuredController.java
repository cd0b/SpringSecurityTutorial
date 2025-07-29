package com.denizcanbagdatlioglu.sociallogin;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class SecuredController {

    @GetMapping("/secured")
    public String secured(Authentication authentication, Model model) {
        if(authentication instanceof UsernamePasswordAuthenticationToken auth) {
            model.addAttribute("username", auth.getName());
        }
        else if(authentication instanceof OAuth2AuthenticationToken auth) {
            model.addAttribute("username", auth.getPrincipal().getAttribute("name"));
        }
        return "secured";
    }

}
