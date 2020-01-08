package pers.dc.ols.service;

import pers.dc.ols.pojo.UserAddress;
import pers.dc.ols.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    List<UserAddress> queryAddressByUserId(String userId);
    void addAddress(AddressBO addressBO);
}
