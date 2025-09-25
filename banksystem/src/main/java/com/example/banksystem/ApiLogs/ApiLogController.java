package com.example.banksystem.ApiLogs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-log")
public class ApiLogController {

    @GetMapping("/test")
    public String hello() {
        return "API Logging!";
    }

}
