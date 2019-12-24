package pers.dc.ols.service;

import pers.dc.ols.pojo.Category;
import pers.dc.ols.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    List<Category> queryAllRootLevelCat();
    List<CategoryVO> queryAllCatIn(int fatherId);
}