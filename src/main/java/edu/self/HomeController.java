package edu.self;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/api/goodmorning")
    public String goodMorning() {
        return "Good morning";
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/api/bye")
    public String bye() {
        return "Hello world";
    }
}
