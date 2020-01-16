package pers.dc.ols.service.center;

import pers.dc.ols.pojo.User;

public interface CenterUserService {

    User queryUserById(String id);
    User updateUser(User user);
    User updateUserFace(String userId, String faceUrl);
}