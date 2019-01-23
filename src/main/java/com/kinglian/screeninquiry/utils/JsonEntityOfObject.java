package com.kinglian.screeninquiry.utils;

import lombok.Data;

import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-22
 */
@Data
public class JsonEntityOfObject {
    private Map<String, String> header;
    private Map<String,Object> body;
}
