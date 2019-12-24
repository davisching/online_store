package pers.dc.ols.service;

import pers.dc.ols.pojo.Category;
import pers.dc.ols.pojo.vo.CategoryVO;
import pers.dc.ols.pojo.vo.RecommendCatVO;

import java.util.List;

public interface CategoryService {
    List<Category> queryAllRootLevelCat();
    List<CategoryVO> queryAllCatIn(int fatherId);
    List<RecommendCatVO> getSixNewItems(int rootId);
}