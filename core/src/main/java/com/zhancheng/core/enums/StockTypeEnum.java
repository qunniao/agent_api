package com.zhancheng.core.enums;

/**
 * @author BianShuHeng
 * @decription
 * @project StockTypeEnum
 * @date 2019/10/22 14:46
 */
public enum StockTypeEnum {

    /**
     * 库存记录变更项
     */
    PURCHASING_ENTITY(1, "采购实体库存"),
    RETAIL_DELIVERY(2, "零售发货"),
    LOWER_SEND_OUT(3, "给下级发货"),
    CLOUD_INVENTORY_PICK_UP(4, "云库存提货"),
    HEADQUARTERS_ADJUST(5, "总部调整"),
    AGENT_ADJUST(6, "代理调整"),
    PICK_UP_GOODS_DELIVERY(7, "提货发货"),
    TRANSPURCHASE_SHIPMENT(8, "转采购发货"),
    CLOUD_INVENTORY_GENERATION(9, "云库存代发"),
    MANUAL_SETTING(10, "手动调整"),
    TRANSFER(11, "划拨"),
    PHYSICAL_GOODS_ARE_EXCHANGED(12, "实体商品换货"),
    REPLACEMENT_OF_CLOUD_INVENTORY(13, "云库存换货"),
    RETAIL_OUTBOUND(14, "零售出库")

    ;

    private int type;
    private String remark;

    StockTypeEnum(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }

}
