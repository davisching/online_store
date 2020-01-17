package pers.dc.ols.mapper;

import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;
import pers.dc.ols.pojo.vo.center.MyCommentVO;

import java.util.List;
import java.util.Map;

public interface CustomCommentMapper {
    List<CommentRecordVO> getComments(String itemId, String level);
    void doComments(Map<String, Object> map);
    List<MyCommentVO> queryMyComments(String userId);

}
