package com.kinglian.screeninquiry.utils;

import lombok.Data;

import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-21
 */
@Data
public class JsonEntity {
    private Map<String, String> header;
    private Map<String,String> body;

}
