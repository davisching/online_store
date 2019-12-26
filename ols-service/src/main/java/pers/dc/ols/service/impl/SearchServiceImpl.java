package pers.dc.ols.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
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

    @Override
    public PagedGridResult getSearchResults(String keywords, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ItemSearchVO> items = itemSearchMapper.getSearchResults(keywords, sort);
        return getResult(items, page, pageSize);
    }
}
