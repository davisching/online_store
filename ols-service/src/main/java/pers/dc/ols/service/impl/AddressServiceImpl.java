package pers.dc.ols.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.UserAddressMapper;
import pers.dc.ols.pojo.User;
import pers.dc.ols.pojo.UserAddress;
import pers.dc.ols.pojo.UserAddressExample;
import pers.dc.ols.pojo.bo.AddressBO;
import pers.dc.ols.service.AddressService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    UserAddressMapper userAddressMapper;

    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAddressByUserId(String userId) {
        UserAddressExample example = new UserAddressExample();
        UserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return userAddressMapper.selectByExample(example);
    }

    @Transactional
    @Override
    public void addAddress(AddressBO addressBO) {
        Integer isDefault = (queryAddressByUserId(addressBO.getUserId()).isEmpty()) ? 1 : 0;
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }

    @Transactional
    @Override
    public void updateAddress(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);

        userAddress.setUpdatedTime(new Date());
        userAddress.setId(addressBO.getAddressId());

        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Transactional
    @Override
    public boolean deleteAddress(String userId, String addressId) {
        UserAddressExample example = new UserAddressExample();
        UserAddressExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(addressId);
        userAddressMapper.deleteByExample(example);
        return true;
    }

    @Override
    public boolean setDefault(String userId, String addressId) {

        UserAddressExample uae = new UserAddressExample();
        UserAddressExample.Criteria criteria = uae.createCriteria();
        criteria.andIsDefaultEqualTo(1);
        List<UserAddress> formers = userAddressMapper.selectByExample(uae);
        if (!formers.isEmpty()) {
            UserAddress former = formers.get(0);
            former.setIsDefault(0);
            userAddressMapper.updateByPrimaryKey(former);
        }

        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddress.setIsDefault(1);
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
        return true;
    }
}