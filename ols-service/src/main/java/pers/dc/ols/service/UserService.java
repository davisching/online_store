package pers.dc.ols.service;

import pers.dc.ols.pojo.User;
import pers.dc.ols.pojo.bo.UserBO;

public interface UserService {
    boolean checkIfUsernameExisted(String username);
    User createUser(UserBO userBO);
    User userLogin(UserBO userBO);

}
