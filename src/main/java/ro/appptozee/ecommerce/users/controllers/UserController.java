package ro.appptozee.ecommerce.users.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {



    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            // removes the authentication from the SecurityContext
            SecurityContextHolder.clearContext();

            // clear session
            HttpSession session = request.getSession(false);
            session = request.getSession(false);
            if(session != null) {
                session.invalidate();
            }

            // clear cookies
            for(Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
        }
        return "redirect:/login-form";
    }
}
