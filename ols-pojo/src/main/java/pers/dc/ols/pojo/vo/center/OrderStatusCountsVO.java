package pers.dc.ols.pojo.vo.center;

import pers.dc.ols.enums.OrderStatusEnum;

import java.util.List;

public class OrderStatusCountsVO {
    private Integer waitPayCounts = 0;
    private Integer waitDeliverCounts = 0;
    private Integer waitReceiveCounts = 0;
    private Integer waitCommentCounts = 0;

    public void initOrderStatusCountsVO(List<PreOrderStatusCountsVO> list) {
        for (PreOrderStatusCountsVO data : list) {
            if (data.getOrderStatusNum() == OrderStatusEnum.WAIT_PAY.type)
                waitPayCounts = data.getCounts();
            else if (data.getOrderStatusNum() == OrderStatusEnum.WAIT_DELIVER.type)
                waitDeliverCounts = data.getCounts();
            else if (data.getOrderStatusNum() == OrderStatusEnum.WAIT_RECEIVE.type)
                waitReceiveCounts = data.getCounts();
            else if (data.getOrderStatusNum() == OrderStatusEnum.SUCCESS.type)
                waitCommentCounts = data.getCounts();
        }
    }

    public Integer getWaitPayCounts() {
        return waitPayCounts;
    }

    public void setWaitPayCounts(Integer waitPayCounts) {
        this.waitPayCounts = waitPayCounts;
    }

    public Integer getWaitDeliverCounts() {
        return waitDeliverCounts;
    }

    public void setWaitDeliverCounts(Integer waitDeliverCounts) {
        this.waitDeliverCounts = waitDeliverCounts;
    }

    public Integer getWaitReceiveCounts() {
        return waitReceiveCounts;
    }

    public void setWaitReceiveCounts(Integer waitReceiveCounts) {
        this.waitReceiveCounts = waitReceiveCounts;
    }

    public Integer getWaitCommentCounts() {
        return waitCommentCounts;
    }

    public void setWaitCommentCounts(Integer waitCommentCounts) {
        this.waitCommentCounts = waitCommentCounts;
    }
}
