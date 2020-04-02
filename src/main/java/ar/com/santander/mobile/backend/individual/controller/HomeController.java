package ar.com.santander.mobile.backend.individual.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public static ResponseEntity<?> home() {
        return ResponseEntity.ok("Welcome!");
    }
}
