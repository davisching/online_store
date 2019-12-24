package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemSpec;
import pers.dc.ols.pojo.vo.ItemInfoVO;
import pers.dc.ols.service.ItemService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "商品相关接口", tags = {"「商品」相关接口"})
@RestController
@RequestMapping("items")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation("获取商品页信息")
    @GetMapping("/info/{itemId}")
    public JSONResult ItemInfo(@PathVariable("itemId") String itemId) {

        if (StringUtils.isBlank(itemId))
            return JSONResult.errorMsg("商品ID不能为空！");

        Item item = itemService.queryItemById(itemId);
        List<ItemImg> itemImgs = itemService.queryItemImgByItemId(itemId);
        List<ItemSpec> itemSpecs = itemService.queryItemSpecByItemId(itemId);
        ItemParam itemParam = itemService.queryItemParamByItemId(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO(item, itemImgs, itemSpecs, itemParam);

        return JSONResult.ok(itemInfoVO);
    }
}