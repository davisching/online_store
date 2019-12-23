package pers.dc.ols.service;

import pers.dc.ols.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    public List<Carousel> queryAll(int isShow);

}