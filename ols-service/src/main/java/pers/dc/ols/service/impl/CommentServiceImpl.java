package pers.dc.ols.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.CustomCommentMapper;
import pers.dc.ols.mapper.ItemCommentMapper;
import pers.dc.ols.pojo.ItemCommentExample;
import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;
import pers.dc.ols.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource CustomCommentMapper customCommentMapper;
    @Resource ItemCommentMapper itemCommentMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CommentRecordVO> getComments(String itemId, String level, Integer from, Integer to) {
        return customCommentMapper.getComments(itemId, level, from, to);
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
