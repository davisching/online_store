package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.ItemImg;
import pers.dc.ols.pojo.ItemImgExample;

public interface ItemImgMapper {
    long countByExample(ItemImgExample example);

    int deleteByExample(ItemImgExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemImg record);

    int insertSelective(ItemImg record);

    List<ItemImg> selectByExample(ItemImgExample example);

    ItemImg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemImg record, @Param("example") ItemImgExample example);

    int updateByExample(@Param("record") ItemImg record, @Param("example") ItemImgExample example);

    int updateByPrimaryKeySelective(ItemImg record);

    int updateByPrimaryKey(ItemImg record);
}