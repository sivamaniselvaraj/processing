package org.assignments.processing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/heartbeat")
    public String heartbeat(){
        return "Processing Service Alive";
    }
}