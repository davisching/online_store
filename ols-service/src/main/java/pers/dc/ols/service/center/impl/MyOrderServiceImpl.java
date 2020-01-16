package pers.dc.ols.service.center.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.CustomOrderMapper;
import pers.dc.ols.pojo.vo.MyOrdersVO;
import pers.dc.ols.service.center.MyOrderService;
import pers.dc.ols.service.common.PagingService;
import pers.dc.ols.utils.PagedGridResult;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyOrderServiceImpl extends PagingService implements MyOrderService {

    @Resource
    private CustomOrderMapper customOrderMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<MyOrdersVO> orders = customOrderMapper.queryMyOrders(userId, orderStatus);
        return getResult(orders, page, pageSize);
    }
}
