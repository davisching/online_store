package pers.dc.ols.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.mapper.TestMapper;
import pers.dc.ols.pojo.Test;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, Boot!";
    }

    @Resource
    TestMapper testMapper;

    @GetMapping("/test")
    public Test test() {
        return testMapper.selectByPrimaryKey(1);
    }
}
