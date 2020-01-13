package pers.dc.ols.service;

import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemSpec;
import pers.dc.ols.pojo.vo.ShopCartItemVO;

import java.util.List;

public interface ItemService {
    Item queryItemById(String id);
    List<ItemImg> queryItemImgByItemId(String itemId);
    List<ItemSpec> queryItemSpecByItemId(String itemId);
    ItemParam queryItemParamByItemId(String itemId);
    List<ShopCartItemVO> queryItemsBySpecId(String specIds);
    void decreaseStock(String specId, Integer buyCounts);
}
