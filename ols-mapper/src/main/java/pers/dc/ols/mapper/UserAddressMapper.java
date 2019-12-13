package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.UserAddress;
import pers.dc.ols.pojo.UserAddressExample;

public interface UserAddressMapper {
    long countByExample(UserAddressExample example);

    int deleteByExample(UserAddressExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}