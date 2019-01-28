/**
 * Copyright (C), 2018-2019, kinglian
 * FileName: MedOvPrescriptionService
 * Author:   weiyz
 * Date:     2019/1/16 19:45
 * Description: MedOvPrescription业务接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.service;

import cn.kinglian.spring.util.Query;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kinglian.screeninquiry.model.entity.MedOvPrescription;

import java.util.List;
import java.util.Map;

/**
 * 〈MedOvPrescription业务接口〉
 *
 * @author weiyz
 * @create 2019/1/16
 * @since 1.0.0
 */
public interface MedOvPrescriptionService extends IService<MedOvPrescription> {

    Page getPreByPreId(Query<Map> query);

    Page getPresOrder(Query<Map> query);

    List<MedOvPrescription> findByVisitId(String visitid);

    Page ObtainPrescriptionPad(Query<Map> mapQuery);
}