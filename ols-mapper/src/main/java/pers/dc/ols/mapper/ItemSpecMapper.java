package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.ItemSpec;
import pers.dc.ols.pojo.ItemSpecExample;

public interface ItemSpecMapper {
    long countByExample(ItemSpecExample example);

    int deleteByExample(ItemSpecExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemSpec record);

    int insertSelective(ItemSpec record);

    List<ItemSpec> selectByExample(ItemSpecExample example);

    ItemSpec selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemSpec record, @Param("example") ItemSpecExample example);

    int updateByExample(@Param("record") ItemSpec record, @Param("example") ItemSpecExample example);

    int updateByPrimaryKeySelective(ItemSpec record);

    int updateByPrimaryKey(ItemSpec record);
}