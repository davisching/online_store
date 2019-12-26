package pers.dc.ols.service.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import pers.dc.ols.utils.PagedGridResult;

import java.util.List;

public class PagingService {

    PagedGridResult result;
    PageInfo<?> pageList;

    public PagedGridResult getResult(List<?> list, int page, int pageSize){
        result = new PagedGridResult();
        pageList = new PageInfo<>(list);
        result.setPage(page);
        result.setRows(list);
        result.setRecords(pageList.getTotal());
        result.setTotal(pageList.getPages());
        return result;
    }
}
