package pers.dc.ols.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @GetMapping("/usernameExisted")
    public HttpStatus usernameExisted(@RequestParam String username) {
        if (StringUtils.isBlank(username) || userService.checkIfUsernameExisted(username))
            return HttpStatus.INTERNAL_SERVER_ERROR;
        return HttpStatus.OK;
    }

}
