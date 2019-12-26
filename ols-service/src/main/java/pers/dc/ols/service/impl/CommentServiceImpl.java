package pers.dc.ols.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.CustomCommentMapper;
import pers.dc.ols.mapper.ItemCommentMapper;
import pers.dc.ols.pojo.ItemCommentExample;
import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;
import pers.dc.ols.service.CommentService;
import pers.dc.ols.utils.PagedGridResult;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource CustomCommentMapper customCommentMapper;
    @Resource ItemCommentMapper itemCommentMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getComments(String itemId, String level, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CommentRecordVO> comments = customCommentMapper.getComments(itemId, level);
        PageInfo<?> pageList = new PageInfo<>(comments);
        PagedGridResult result = new PagedGridResult();
        result.setPage(page);
        result.setRows(comments);
        result.setTotal(pageList.getPages());
        result.setRecords(pageList.getTotal());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public int getCounts(String itemId, Integer level) {
        ItemCommentExample example = new ItemCommentExample();
        ItemCommentExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        criteria.andCommentLevelEqualTo(level);
        return (int) itemCommentMapper.countByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CountsVO getCounts(String itemId) {
        CountsVO countsVO = new CountsVO();
        countsVO.setGoodCounts(getCounts(itemId, 1));
        countsVO.setNormalCounts(getCounts(itemId, 2));
        countsVO.setBadCounts(getCounts(itemId, 3));
        countsVO.sum();
        return countsVO;
    }


}
