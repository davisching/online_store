package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.enums.YesOrNo;
import pers.dc.ols.service.CarouselService;
import pers.dc.ols.service.CategoryService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@Api(value = "首页相关接口", tags = {"「首页」相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private CarouselService carouselService;

    @Resource
    private CategoryService categoryService;

    @ApiOperation("获取轮播图列表")
    @GetMapping("/carousel")
    public JSONResult getCarousel() {
        return JSONResult.ok(carouselService.queryAll(YesOrNo.Yes.type));
    }

    @ApiOperation("获取根级分类")
    @GetMapping("/cats")
    public JSONResult getRootCategory() {
        return JSONResult.ok(categoryService.queryAllRootLevelCat());
    }

    @ApiOperation("获取子级分类")
    @GetMapping("/subCat/{fatherId}")
    public JSONResult subCat (
            @ApiParam(value = "父级分类ID", required = true)
            @PathVariable("fatherId") Integer fatherId) {
        if (fatherId == null)
            return JSONResult.errorMsg("分类不存在");
        return JSONResult.ok(categoryService.queryAllCatIn(fatherId));
    }

    @ApiOperation("获取各分类推荐商品")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems (
            @ApiParam(value = "根级分类ID", required = true)
            @PathVariable("rootCatId") Integer rootCatId ) {
        if (rootCatId == null)
            return JSONResult.errorMsg("分类不存在");
        return JSONResult.ok(categoryService.getSixNewItems(rootCatId));
    }
}