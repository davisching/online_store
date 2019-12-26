package pers.dc.ols.service;

import pers.dc.ols.pojo.vo.ItemSearchVO;
import pers.dc.ols.utils.PagedGridResult;

import java.util.List;

public interface SearchService {
    PagedGridResult getSearchResults(String keywords, String sort, Integer page, Integer pageSize);
}
