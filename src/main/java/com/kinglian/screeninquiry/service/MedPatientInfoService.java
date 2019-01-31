/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: MedPatientInfoService
 * Author:   weiyz
 * Date:     2019/1/16 19:47
 * Description: MedPatientInfoService业务接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.MedPatientInfo;

/**
 * 〈MedPatientInfoService业务接口〉
 *
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
public interface MedPatientInfoService extends IService<MedPatientInfo> {

    void updateById(String portalid);
}