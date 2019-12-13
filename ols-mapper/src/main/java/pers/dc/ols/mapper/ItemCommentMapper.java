package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.ItemComment;
import pers.dc.ols.pojo.ItemCommentExample;

public interface ItemCommentMapper {
    long countByExample(ItemCommentExample example);

    int deleteByExample(ItemCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemComment record);

    int insertSelective(ItemComment record);

    List<ItemComment> selectByExample(ItemCommentExample example);

    ItemComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemComment record, @Param("example") ItemCommentExample example);

    int updateByExample(@Param("record") ItemComment record, @Param("example") ItemCommentExample example);

    int updateByPrimaryKeySelective(ItemComment record);

    int updateByPrimaryKey(ItemComment record);
}