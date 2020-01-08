package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.UserAddress;
import pers.dc.ols.pojo.bo.AddressBO;
import pers.dc.ols.service.AddressService;
import pers.dc.ols.utils.JSONResult;
import pers.dc.ols.utils.MobileEmailUtils;

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

        JSONResult result = checkAddress(addressBO);

        if (result.isOK())
            addressService.addAddress(addressBO);

        return result;
    }

    @ApiOperation("修改用户地址")
    @PostMapping("/update")
    public JSONResult update (@RequestBody AddressBO addressBO) {

        JSONResult result = checkAddress(addressBO);

        if (result.isOK())
            addressService.updateAddress(addressBO);

        return result;
    }

    private JSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver))
            return JSONResult.errorMsg("收货人不能为空");
        if (receiver.length() > 12)
            return JSONResult.errorMsg("收货人名称不能太长");

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile))
            return JSONResult.errorMsg("手机号不能为空");
        if (mobile.length() != 11)
            return JSONResult.errorMsg("手机号长度不正确");
        if (!MobileEmailUtils.checkMobileIsOk(mobile))
            return JSONResult.errorMsg("手机格式不正确");

//        if (addressBO.getCity().isEmpty()
//                || addressBO.getProvince().isEmpty()
//                || addressBO.getDistrict().isEmpty()
//                || addressBO.getDetail().isEmpty())
//            return JSONResult.errorMsg("地址不能为空");

        if (addressBO.getDetail().isEmpty())
            return JSONResult.errorMsg("地址不能为空");

        return JSONResult.ok();
    }

}