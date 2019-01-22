/**
 * Copyright (C), 2018-2019,Kinglian
 * FileName: MedOvPrescriptionController
 * Author:   weiyz
 * Date:     2019/1/17 14:25
 * Description: MedOvPrescription控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.util.Query;
import cn.kinglian.spring.util.R;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.model.entity.DocEvaluation;
import com.kinglian.screeninquiry.model.entity.MedOvPresSheet;
import com.kinglian.screeninquiry.model.entity.MedOvPrescription;
import com.kinglian.screeninquiry.service.DocEvaluationService;
import com.kinglian.screeninquiry.service.MedOvPresSheetService;
import com.kinglian.screeninquiry.service.MedOvPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 〈MedOvPrescription控制器〉
 * @author weiyz
 * @create 2019/1/17
 * @since 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/screenInquiry/prescription")
public class MedOvPrescriptionController {

    @Autowired
    MedOvPrescriptionService medOvPrescriptionService;

    @Autowired
    MedOvPresSheetService medOvPresSheetService;

    @Autowired
    DocEvaluationService docEvaluationService;

    @GetMapping("getPrescription")
    public R<Page> getPrescription(@RequestParam Map<String, Object> params){
        Page page = medOvPrescriptionService.getPreByPreId(new Query<Map>(params));
        return new R<>(page);
    }


    @GetMapping("getPresOrder")
    public R<Page> getPresOrder(@RequestParam Map<String, Object> params){
        return new R<>(medOvPrescriptionService.getPresOrder(new Query<Map>(params)));
    }

    /**
     * 审核处方
     * @param sheetid
     * @param auditStatus
     * @return
     */
    @GetMapping("review/{sheetid}/{auditStatus}")
    public R<Boolean> reviewPrescription(@PathVariable String sheetid,@PathVariable String auditStatus){
        MedOvPresSheet medOvPresSheet = new MedOvPresSheet();
        medOvPresSheet.setAuditStatus(Integer.parseInt(auditStatus));
//        medOvPresSheet.setSheetid(sheetid);
        return new R<>(medOvPresSheetService.update(medOvPresSheet,new EntityWrapper<MedOvPresSheet>().eq("sheetid",sheetid)));
    }

    @PostMapping("evaluate")
    public R<Boolean> evaluateDoctor(@RequestBody DocEvaluation docEvaluation){
        return new R<>(docEvaluationService.insert(docEvaluation));
    }

}
