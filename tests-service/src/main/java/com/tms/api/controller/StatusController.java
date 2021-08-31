package com.tms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prop")
public class StatusController {

    @Autowired
    Environment env;

    @GetMapping
    public String status() {
        return " my.property is:  " + env.getProperty("my.property");
    }
}
