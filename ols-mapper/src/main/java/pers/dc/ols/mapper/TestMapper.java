package pers.dc.ols.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.dc.ols.pojo.Test;

@Mapper
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}