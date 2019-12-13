package pers.dc.ols.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.UserMapper;
import pers.dc.ols.pojo.User;
import pers.dc.ols.pojo.UserExample;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean checkIfUsernameExisted(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample).size() > 0;
    }

}
