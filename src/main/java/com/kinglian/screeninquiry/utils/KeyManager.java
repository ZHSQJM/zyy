package com.kinglian.screeninquiry.utils;

import com.alibaba.fastjson.JSONObject;
import com.kinglian.screeninquiry.model.dto.AccessKeyAndTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HXC
 * @date 2019-1-31
 */
public class KeyManager {

    public static Map<String, AccessKeyAndTime> keys = new HashMap<>();

    private KeyManager (){}

    public static String getValue(String key) {
        AccessKeyAndTime data = keys.get(key);
        if (data == null) {
            return null;
        }
        if (System.currentTimeMillis()- data.getTime() > 2 * 60 * 60 * 1000 ) {
            keys.remove(key);
            return null;
        }
        return data.getAccessKey();
    }

    public static void putValue(String key,String accessKey) {
        AccessKeyAndTime accessKeyAndTime = new AccessKeyAndTime();
        accessKeyAndTime.setAccessKey(accessKey);
        accessKeyAndTime.setTime(System.currentTimeMillis());
        keys.put(key, accessKeyAndTime);
    }
}
