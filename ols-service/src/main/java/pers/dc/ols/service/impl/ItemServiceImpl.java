package pers.dc.ols.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.*;
import pers.dc.ols.pojo.*;
import pers.dc.ols.pojo.vo.ShopCartItemVO;
import pers.dc.ols.service.ItemService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource ItemMapper itemMapper;
    @Resource ItemImgMapper itemImgMapper;
    @Resource ItemSpecMapper itemSpecMapper;
    @Resource ItemParamMapper itemParamMapper;
    @Resource ItemMapperCustom itemMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Item queryItemById(String id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemImg> queryItemImgByItemId(String itemId) {
        ItemImgExample ie = new ItemImgExample();
        ItemImgExample.Criteria ic = ie.createCriteria();
        ic.andItemIdEqualTo(itemId);
        return itemImgMapper.selectByExample(ie);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemSpec> queryItemSpecByItemId(String itemId) {
        ItemSpecExample ie = new ItemSpecExample();
        ItemSpecExample.Criteria ic = ie.createCriteria();
        ic.andItemIdEqualTo(itemId);
        return itemSpecMapper.selectByExample(ie);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemParam queryItemParamByItemId(String itemId) {
        ItemParamExample ie = new ItemParamExample();
        ItemParamExample.Criteria ic = ie.createCriteria();
        ic.andItemIdEqualTo(itemId);
        List<ItemParam> list = itemParamMapper.selectByExample(ie);
        return list != null ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopCartItemVO> queryItemsBySpecId(String specIds) {
        String[] ids = specIds.split(",");
        return itemMapperCustom.queryItemsBySpecId(Arrays.asList(ids));
    }

    @Transactional
    @Override
    public void decreaseStock(String specId, Integer buyCounts) {

//        ItemSpec itemSpec = itemSpecMapper.selectByPrimaryKey(specId);
//        Integer curStock = itemSpec.getStock();
//
//        // TODO 将来会用 zookeeper 或 redis 进行 分布式锁 处理
//
//        if (curStock < buyCounts) {
//
//        }

        int result = itemMapperCustom.decreaseStock(specId, buyCounts);
        if (result != 1)
            throw new RuntimeException("库存不足");
    }
}
