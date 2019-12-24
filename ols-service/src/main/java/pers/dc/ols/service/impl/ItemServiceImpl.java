package pers.dc.ols.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.ItemImgMapper;
import pers.dc.ols.mapper.ItemMapper;
import pers.dc.ols.mapper.ItemParamMapper;
import pers.dc.ols.mapper.ItemSpecMapper;
import pers.dc.ols.pojo.*;
import pers.dc.ols.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource ItemMapper itemMapper;
    @Resource ItemImgMapper itemImgMapper;
    @Resource ItemSpecMapper itemSpecMapper;
    @Resource ItemParamMapper itemParamMapper;

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
}
