package io.gagan.rest_demo;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public WelcomeBean welcome(@RequestParam String msg){
        return new WelcomeBean(msg);
    }
}
