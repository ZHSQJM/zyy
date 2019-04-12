package com.kinglian.screeninquiry.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/9 14:26
 * @package: com.kinglian.screeninquiry.config
 * @description:
 */
@Data
@Component
@Configuration
public class OSSConfig {

    @Value("${kuakua.endpoint}")
    private   String LXIMAGE_END_POINT;
    @Value("${kuakua.keyid}")
    private  String LXIMAGE_ACCESS_KEY_ID;
    @Value("${kuakua.keysecret}")
    private  String LXIMAGE_ACCESS_KEY_SECRET;
    @Value("${kuakua.filehost}")
    private  String LXIMAGE_FILE_HOST;
    @Value("${kuakua.bucketname}")
    private  String LXIMAGE_BUCKET_NAME;
}
