package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.Date;

@Api(tags = "「用户中心-用户」相关接口")
@RequestMapping("userInfo")
@RestController
public class CenterUserController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public JSONResult update(String userId, @RequestBody User user,
                             HttpServletRequest request, HttpServletResponse response) {
        User newUser = centerUserService.updateUser(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(newUser), true);
        return JSONResult.ok();
    }
}
