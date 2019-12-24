package pers.dc.ols.service;

import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemSpec;

import java.util.List;

public interface ItemService {
    Item queryItemById(String id);
    List<ItemImg> queryItemImgByItemId(String itemId);
    List<ItemSpec> queryItemSpecByItemId(String itemId);
    ItemParam queryItemParamByItemId(String itemId);
}
