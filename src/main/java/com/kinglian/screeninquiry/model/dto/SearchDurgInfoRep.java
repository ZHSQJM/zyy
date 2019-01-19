package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-19
 */
@Data
public class SearchDurgInfoRep {
    private SearchDurgBody body;
    private String command;
    private String resultCode;
    private String resultMsg;
    private String timestamp;
}
