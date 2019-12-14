package pers.dc.ols.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


import javax.annotation.Resource;

@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, Boot!";
    }

}
