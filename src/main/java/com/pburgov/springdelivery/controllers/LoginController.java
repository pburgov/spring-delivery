package com.pburgov.springdelivery.controllers;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    protected final Log logger = LogFactory.getLog(this.getClass());


    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "login";
    }


    @GetMapping({"/login", "/"})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {
		

		  if(principal != null) { flash.addFlashAttribute("info",
		  "You have signed in before"); return "redirect:/repartos/list"; }


        if (error != null) {
            model.addAttribute("error", "Login Incorrect: User or Password incorrect. Please try again!");
        }

        if (logout != null) {
            model.addAttribute("success", "You have closed session successfully!");
        }

        return "login";
    }

    @Controller
    static class FaviconController {
        @GetMapping("/favicon.ico")
        @ResponseBody
        void returnNoFavicon() {

        }
    }
}
