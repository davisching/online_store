package pers.dc.ols.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.service.UserService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @GetMapping("/usernameExisted")
    public JSONResult usernameExisted(@RequestParam String username) {

        if (StringUtils.isBlank(username))
            return JSONResult.errorMsg("用户名不能为空");

        if (userService.checkIfUsernameExisted(username))
            return JSONResult.errorMsg("用户名已存在");

        return JSONResult.ok();
    }



}
