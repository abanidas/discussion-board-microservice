package com.abani.microservice.discussionboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/service-fallback")
    public ResponseEntity<String> userServiceFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Server is not responding. Please try again later!");
    }
}
