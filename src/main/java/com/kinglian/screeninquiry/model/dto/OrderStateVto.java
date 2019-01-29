package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

@Data
public class OrderStateVto {

    //1:待付款；16：已关闭；7：已完成
    public String orderState;

    public String payState;

}
