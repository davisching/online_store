package pers.dc.ols.mapper;

import java.util.List;

public interface CategoryMapperCustom {
    List getSubCatList(Integer rootCatId);
    List getSixNewItems(Integer rootCatId);
}
