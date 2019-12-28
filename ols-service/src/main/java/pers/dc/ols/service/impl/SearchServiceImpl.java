package pers.dc.ols.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.ItemSearchMapper;
import pers.dc.ols.pojo.vo.ItemSearchVO;
import pers.dc.ols.service.SearchService;
import pers.dc.ols.service.common.PagingService;
import pers.dc.ols.utils.PagedGridResult;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchServiceImpl extends PagingService implements SearchService {

    @Resource
    ItemSearchMapper itemSearchMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getSearchResults(String keywords, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ItemSearchVO> items = itemSearchMapper.getSearchResults(keywords, sort);
        return getResult(items, page, pageSize);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getSearchResultsByCatId(String catId, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ItemSearchVO> items = itemSearchMapper.getSearchResultsByCatId(catId, sort);
        return getResult(items, page, pageSize);
    }
}
