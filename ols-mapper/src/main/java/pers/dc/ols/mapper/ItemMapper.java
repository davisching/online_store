package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.Item;
import pers.dc.ols.pojo.ItemExample;

public interface ItemMapper {
    long countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExampleWithBLOBs(ItemExample example);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExampleWithBLOBs(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);
}