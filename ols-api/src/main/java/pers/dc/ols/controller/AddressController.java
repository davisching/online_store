package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.UserAddress;
import pers.dc.ols.pojo.bo.AddressBO;
import pers.dc.ols.service.AddressService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "「地址」相关接口")
@RequestMapping("address")
@RestController
public class AddressController {

    @Resource
    AddressService addressService;

    @ApiOperation("根据用户ID查询地址")
    @PostMapping("/list")
    public JSONResult listAddressByUserId (@RequestParam String userId) {
        if (StringUtils.isBlank(userId))
            return JSONResult.errorMsg("");
        return JSONResult.ok(addressService.queryAddressByUserId(userId));
    }

    @ApiOperation("添加用户地址")
    @PostMapping("/add")
    public JSONResult add (@RequestBody AddressBO addressBO) {
        if (addressBO == null)
            return JSONResult.errorMsg("");
        addressService.addAddress(addressBO);
        return JSONResult.ok();
    }
}