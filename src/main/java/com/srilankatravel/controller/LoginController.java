package com.srilankatravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "success", required = false) String success,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        // These parameters will be available in the template as ${param.error}, ${param.success}, etc.
        return "login";
    }
}