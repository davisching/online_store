package pers.dc.ols.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.dc.ols.pojo.Carousel;
import pers.dc.ols.pojo.CarouselExample;

public interface CarouselMapper {
    long countByExample(CarouselExample example);

    int deleteByExample(CarouselExample example);

    int deleteByPrimaryKey(String id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    List<Carousel> selectByExample(CarouselExample example);

    Carousel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Carousel record, @Param("example") CarouselExample example);

    int updateByExample(@Param("record") Carousel record, @Param("example") CarouselExample example);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
}