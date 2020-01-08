package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.bo.ShopCartItemBO;
import pers.dc.ols.service.ItemService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = {"「购物车」相关接口"})
@RequestMapping("shopcart")
@RestController
public class ShopCartController {

    @ApiOperation("添加商品至购物车")
    @PostMapping("/add")
    public JSONResult add(@RequestParam("userId") String userId,
                          @RequestBody ShopCartItemBO shopCartItemBO,
                          HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isBlank(userId))
            return JSONResult.errorMsg("");

        // TODO 需同步至后端redis缓存

        return JSONResult.ok();
    }
}
