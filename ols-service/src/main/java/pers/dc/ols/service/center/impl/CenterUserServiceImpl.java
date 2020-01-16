package pers.dc.ols.service.center.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.UserMapper;
import pers.dc.ols.pojo.User;
import pers.dc.ols.service.center.CenterUserService;

import javax.annotation.Resource;
import java.util.Date;

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

    @Transactional
    @Override
    public User updateUser(User user) {
        user.setUpdatedTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return setParamNull(queryUserById(user.getId()));
    }

    @Transactional
    @Override
    public User updateUserFace(String userId, String faceUrl) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setFace(faceUrl);
        user.setUpdatedTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(userId);
    }

    // TODO 到时候用 redis 会改写这个方法
    private User setParamNull(User user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setRealname(null);
        user.setRealname(null);
        return user;
    }
}
