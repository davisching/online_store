package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.ItemParam;
import pers.dc.ols.pojo.ItemParamExample;

public interface ItemParamMapper {
    long countByExample(ItemParamExample example);

    int deleteByExample(ItemParamExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    List<ItemParam> selectByExample(ItemParamExample example);

    ItemParam selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByExample(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKey(ItemParam record);
}