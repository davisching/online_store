package pers.dc.ols.mapper;

import pers.dc.ols.pojo.vo.ItemSearchVO;

import java.util.List;

public interface ItemSearchMapper {
    List<ItemSearchVO> getSearchResults(String keywords, String sort);
}
