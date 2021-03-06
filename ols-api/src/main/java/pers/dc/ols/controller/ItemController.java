package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemSpec;
import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;
import pers.dc.ols.pojo.vo.ItemInfoVO;
import pers.dc.ols.service.CommentService;
import pers.dc.ols.service.ItemService;
import pers.dc.ols.service.SearchService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "商品相关接口", tags = {"「商品」相关接口"})
@RestController
@RequestMapping("items")
public class ItemController {

    @Resource private ItemService itemService;
    @Resource private CommentService commentService;
    @Resource private SearchService searchService;

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

    @ApiOperation("获取商品评论")
    @GetMapping("/comments")
    public JSONResult getComments( @RequestParam("itemId") String itemId,
                                   @RequestParam(value = "level", required = false) String level,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 10;
        return JSONResult.ok(commentService.getComments(itemId, level, page, pageSize));
    }

    @ApiOperation("获取商品各评级个数")
    @GetMapping("/commentLevel")
    public JSONResult getCommentLevel(@RequestParam("itemId") String itemId) {
        return JSONResult.ok(commentService.getCounts(itemId));
    }

    @ApiOperation("商品搜索")
    @GetMapping("/search")
    public JSONResult search( @RequestParam("keywords") String keywords,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (sort == null) sort = "k";
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 20;
        return JSONResult.ok(searchService.getSearchResults(keywords, sort, page, pageSize));
    }

    @ApiOperation("商品搜索（通过商品分类）")
    @GetMapping("/catItems")
    public JSONResult searchByCatId( @RequestParam("catId") String catId,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (sort == null) sort = "k";
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 20;
        return JSONResult.ok(searchService.getSearchResultsByCatId(catId, sort, page, pageSize));
    }

    @ApiOperation("根据商品规格id查询商品数据")
    @GetMapping("/refresh")
    public JSONResult refresh(@RequestParam(required = false) String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds))
            return JSONResult.ok();
        return JSONResult.ok(itemService.queryItemsBySpecId(itemSpecIds));
    }
}