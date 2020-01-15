package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.pojo.User;
import pers.dc.ols.service.center.CenterUserService;
import pers.dc.ols.utils.CookieUtils;
import pers.dc.ols.utils.JSONResult;
import pers.dc.ols.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "「用户中心-用户」相关接口")
@RequestMapping("userInfo")
@RestController
public class CenterUserController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public JSONResult update(String userId, @Valid @RequestBody User user, BindingResult bindingResult,
                             HttpServletRequest request, HttpServletResponse response) {
        User newUser = centerUserService.updateUser(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors())
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return JSONResult.errorMap(errorMap);
        }
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(newUser), true);
        return JSONResult.ok();
    }
}
