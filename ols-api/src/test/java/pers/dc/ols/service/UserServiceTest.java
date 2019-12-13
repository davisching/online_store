package pers.dc.ols.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.dc.ols.Application;
import pers.dc.ols.pojo.bo.UserBO;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    public void createUserTest() {
        UserBO userBO = new UserBO();
        userBO.setUsername("davisching");
        userBO.setPassword("Aa941211");
        System.out.println(userService.createUser(userBO));
    }

}