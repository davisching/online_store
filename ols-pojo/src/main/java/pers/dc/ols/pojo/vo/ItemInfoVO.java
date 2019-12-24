package pers.dc.ols.pojo.vo;

import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemSpec;

import java.util.List;

public class ItemInfoVO {

    private Item item;
    private List<ItemImg> itemImgList;
    private List<ItemSpec> itemSpecList;
    private ItemParam itemParams;

    public ItemInfoVO(Item item, List<ItemImg> itemImgList, List<ItemSpec> itemSpecList, ItemParam itemParams) {
        this.item = item;
        this.itemImgList = itemImgList;
        this.itemSpecList = itemSpecList;
        this.itemParams = itemParams;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemParam itemParams) {
        this.itemParams = itemParams;
    }
}
