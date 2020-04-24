package com.zhancheng.core.enums;

/**
 * @author BianShuHeng
 * @decription
 * @project OrderStateEnum
 * @date 2019/10/30 14:15
 */
public enum OrderStateEnum {

    DELETE(-1, "已删除"),
    CLOSE(0, "已关闭"),
    AWAIT_UNDETERMINED(1, "待确认"),
    AWAIT_PAYMENT(2, "待付款"),
    AWAIT_HQ_UNDETERMINED(3, "待总部处理"),
    AWAIT_SEND_OUT (4, "待发货"),
    AWAIT_RECEIVING(5, "待收货"),
    AWAIT_EVALUATE (6, "待评价"),
    COMPLETED(7, "已完成"),
    WAIT_HQ_REVIEW(1, "已上传凭证,等待总部审核"),
    RATIFY_VOUCHER(2, "凭证已通过"),
    REJECT_VOUCHER(3, "凭证已驳回")


    ;

    private int state;
    private String remark;

    OrderStateEnum(int state, String remark) {
        this.state = state;
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public String getRemark() {
        return remark;
    }
}
