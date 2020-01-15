package pers.dc.ols.service.center.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.UserMapper;
import pers.dc.ols.pojo.User;
import pers.dc.ols.service.center.CenterUserService;

import javax.annotation.Resource;

@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(null);
        return user;
    }
}
