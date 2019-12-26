package pers.dc.ols.service;

import pers.dc.ols.pojo.vo.CommentRecordVO;
import pers.dc.ols.pojo.vo.CountsVO;
import pers.dc.ols.utils.PagedGridResult;

import java.util.List;

public interface CommentService {
    PagedGridResult getComments(String itemId, String level, Integer page, Integer pageSize);
    int getCounts(String itemId, Integer level);
    CountsVO getCounts(String itemId);
}
