package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.User;
import pers.dc.ols.pojo.bo.UserBO;
import pers.dc.ols.service.UserService;
import pers.dc.ols.utils.CookieUtils;
import pers.dc.ols.utils.JSONResult;
import pers.dc.ols.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录", tags = {"「注册登录」相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "判断用户名是否存在", notes = "判断用户名是否存在")
    @GetMapping("/check-availability")
    public JSONResult usernameExisted(@RequestParam String username) {

        if (StringUtils.isBlank(username))
            return JSONResult.errorMsg("用户名不能为空！");

        if (userService.checkIfUsernameExisted(username))
            return JSONResult.errorMsg("用户名已存在！");

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用于用户注册")
    @PostMapping("/register")
    public JSONResult register(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {

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

        User user = userService.createUser(userBO);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            return JSONResult.errorMsg("用户名或密码不能为空！");

        User user = userService.userLogin(userBO);

        if (user == null)
            return JSONResult.errorMsg("用户名或密码错误");

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return JSONResult.ok();
    }
}
