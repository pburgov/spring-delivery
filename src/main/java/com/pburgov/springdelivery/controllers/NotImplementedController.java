package com.pburgov.springdelivery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotImplementedController {


    @GetMapping("/not_implemented_yet")
    public String notImplemented(){
        return "not_implemented_yet";
    }
}
