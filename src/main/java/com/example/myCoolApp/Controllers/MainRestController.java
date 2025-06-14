package com.example.myCoolApp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    //expose "/" that return hello shaimaa

    @GetMapping("/")
    public String sayHello(){
        return "Hello Shaimaa";
     }
}
