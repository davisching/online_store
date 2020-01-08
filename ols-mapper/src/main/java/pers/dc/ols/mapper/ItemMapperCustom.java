package pers.dc.ols.mapper;

import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.vo.ShopCartItemVO;

import java.util.List;

public interface ItemMapperCustom {
    List<ShopCartItemVO> queryItemsBySpecId(@Param("specIds") List<String> specIds);
}
