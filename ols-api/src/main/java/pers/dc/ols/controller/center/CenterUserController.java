package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.dc.ols.pojo.User;
import pers.dc.ols.resources.DevProperties;
import pers.dc.ols.service.center.CenterUserService;
import pers.dc.ols.utils.CookieUtils;
import pers.dc.ols.utils.DateUtil;
import pers.dc.ols.utils.JSONResult;
import pers.dc.ols.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "「用户中心-用户」相关接口")
@RequestMapping("userInfo")
@RestController
public class CenterUserController {

    @Resource
    private CenterUserService centerUserService;

    @Resource
    private DevProperties devProperties;

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public JSONResult update(String userId, @Valid @RequestBody User user, BindingResult bindingResult,
                             HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors())
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return JSONResult.errorMap(errorMap);
        }
        User newUser = centerUserService.updateUser(user);
        updateUserCookie(newUser, request, response);
        return JSONResult.ok();
    }

    @ApiOperation("用户头像上传")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(String userId, MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response) {
        if (file == null) return JSONResult.errorMsg("文件不能为空!");
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf('.'));

        if (!suffix.equalsIgnoreCase(".jpg")
                && !suffix.equalsIgnoreCase(".jpeg")
                && !suffix.equalsIgnoreCase(".png"))
            return JSONResult.errorMsg("文件格式不支持");

        filename = "face-" + userId + suffix;
        File out = new File(getStoringLocation(filename));
        if (out.getParentFile() != null)
            out.getParentFile().mkdirs();
        try {
            file.transferTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newUrl = "http://172.17.49.24:8088/ols/face/"+filename
                +"?t="+ DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        User user = centerUserService.updateUserFace(userId, newUrl);
        updateUserCookie(user, request, response);
        return JSONResult.ok();
    }

    private void updateUserCookie(User user, HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
    }

    private String getStoringLocation(String filename) {
        return devProperties.getFaceStoringLocation() + filename;
    }
}
