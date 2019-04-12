package com.kinglian.screeninquiry.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/9 15:41
 * @package: com.kinglian.screeninquiry.service
 * @description:
 */
public interface uploadService {

    String upload(MultipartFile file);
}
