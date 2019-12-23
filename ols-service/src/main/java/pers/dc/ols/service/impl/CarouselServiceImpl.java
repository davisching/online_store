package pers.dc.ols.service.impl;

import org.springframework.stereotype.Service;
import pers.dc.ols.mapper.CarouselMapper;
import pers.dc.ols.pojo.Carousel;
import pers.dc.ols.pojo.CarouselExample;
import pers.dc.ols.service.CarouselService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(int isShow) {
        CarouselExample ce = new CarouselExample();
        CarouselExample.Criteria cc = ce.createCriteria();
        cc.andIsShowEqualTo(isShow);
        ce.setOrderByClause("is_show desc");
        return carouselMapper.selectByExample(ce);
    }
}
