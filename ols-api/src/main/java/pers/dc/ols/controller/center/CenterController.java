package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.pojo.User;
import pers.dc.ols.service.center.CenterUserService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@Api(tags = "「用户中心」相关接口")
@RequestMapping("center")
@RestController
public class CenterController {

    @Resource
    private CenterUserService centerUserService;

    @GetMapping("/userInfo")
    public JSONResult userInfo(String userId) {
        User user = centerUserService.queryUserById(userId);
        if (user == null)
            return JSONResult.errorMsg("用户不存在");
        return JSONResult.ok(user);
    }

}
