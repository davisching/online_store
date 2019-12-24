package pers.dc.ols.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.CategoryMapper;
import pers.dc.ols.mapper.CategoryMapperCustom;
import pers.dc.ols.pojo.Category;
import pers.dc.ols.pojo.CategoryExample;
import pers.dc.ols.pojo.vo.CategoryVO;
import pers.dc.ols.pojo.vo.RecommendCatVO;
import pers.dc.ols.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        CategoryExample ce = new CategoryExample();
        CategoryExample.Criteria cc = ce.createCriteria();
        cc.andFatherIdEqualTo(0);
        return categoryMapper.selectByExample(ce);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> queryAllCatIn(int fatherId) {
        return categoryMapperCustom.getSubCatList(fatherId);
    }

    @Override
    public List<RecommendCatVO> getSixNewItems(int rootId) {
        return categoryMapperCustom.getSixNewItems(rootId);
    }
}
