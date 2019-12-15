package pers.dc.ols.service;

import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.enums.Gender;
import pers.dc.ols.mapper.UserMapper;
import pers.dc.ols.pojo.User;
import pers.dc.ols.pojo.UserExample;
import pers.dc.ols.pojo.bo.UserBO;
import pers.dc.ols.utils.DateUtil;
import pers.dc.ols.utils.MD5Utils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    // 用于自动创建用户ID的方法
    @Resource
    private Sid sid;

    private static final String USER_AVATAR = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean checkIfUsernameExisted(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample).size() > 0;
    }

    @Override
    @Transactional
    public User createUser(UserBO userBO) {
        User user = new User();
        user.setId(sid.nextShort());
        user.setUsername(userBO.getUsername());
        user.setPassword(MD5Utils.doCrypt(userBO.getPassword()));
        user.setNickname(userBO.getUsername());
        user.setFace(USER_AVATAR);
        user.setBirthday(DateUtil.stringToDate("1970-01-01"));
        user.setSex(Gender.CONFIDENTIAL.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User userLogin(UserBO userBO) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userBO.getUsername());
        criteria.andPasswordEqualTo(MD5Utils.doCrypt(userBO.getPassword()));
        List<User> results = userMapper.selectByExample(userExample);
        if (results.size() != 1) return null;
        return setParamNull(results.get(0));
    }

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