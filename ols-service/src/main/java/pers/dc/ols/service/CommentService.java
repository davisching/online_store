package pers.dc.ols.service;

import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;

import java.util.List;

public interface CommentService {
    List<CommentRecordVO> getComments(String itemId, String level, Integer from, Integer to);
    int getCounts(String itemId, Integer level);
    CountsVO getCounts(String itemId);
}
