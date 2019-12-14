package pers.dc.ols.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.bo.UserBO;
import pers.dc.ols.service.UserService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @GetMapping("/check-availability")
    public JSONResult usernameExisted(@RequestParam String username) {

        if (StringUtils.isBlank(username))
            return JSONResult.errorMsg("用户名不能为空！");

        if (userService.checkIfUsernameExisted(username))
            return JSONResult.errorMsg("用户名已存在！");

        return JSONResult.ok();
    }

    @PostMapping("/register")
    public JSONResult register(@RequestBody UserBO userBO) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword))
            return JSONResult.errorMsg("用户名或密码不能为空！");

        if (userService.checkIfUsernameExisted(username))
            return JSONResult.errorMsg("用户名已存在！");

        if (password.length() < 6)
            return JSONResult.errorMsg("密码长度不能小于6位！");

        if (!password.equals(confirmPassword))
            return JSONResult.errorMsg("两次密码不相同！");

        userService.createUser(userBO);

        return JSONResult.ok();
    }
}
