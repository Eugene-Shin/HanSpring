package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
public class PageController {

    @GetMapping("/page")
    public String page() {
        return "hello";
    }

    @DeleteMapping("/page")
    public String page1() {
        return "dello";
    }
}