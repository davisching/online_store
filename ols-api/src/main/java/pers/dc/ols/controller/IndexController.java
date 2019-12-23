package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.enums.YesOrNo;
import pers.dc.ols.service.CarouselService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@Api(value = "首页相关接口", tags = {"「首页」相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private CarouselService carouselService;

    @ApiOperation("获取轮播图列表")
    @GetMapping("/carousel")
    public JSONResult getCarousel() {
        return JSONResult.ok(carouselService.queryAll(YesOrNo.Yes.type));
    }
}