package com.kinglian.screeninquiry.controller;

import cn.kinglian.spring.config.LoginException;
import cn.kinglian.spring.util.R;
import com.kinglian.screeninquiry.model.dto.SaveCase;
import com.kinglian.screeninquiry.service.DoctorOperationService;
import com.kinglian.screeninquiry.service.MedOfficeVisitService;
import com.kinglian.screeninquiry.service.MedOvMedicalRecordService;
import com.kinglian.screeninquiry.service.UserService;
import com.kinglian.screeninquiry.utils.JsonEntity;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @author HXC
 * @date 2019-1-16
 */
@RestController
@RequestMapping("/screenInquiry/doctor")
public class DoctorOperationController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorOperationService doctorOperationService;

    @Autowired
    private MedOfficeVisitService medOfficeVisitService;

    @Autowired
    private MedOvMedicalRecordService medOvMedicalRecordService;

    /**
     * 医生端登录接口
     * @return
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody JsonEntity jsonEntity) throws Exception {
        if (!userService.login(jsonEntity.getBody().get("user"), jsonEntity.getBody().get("password"))) {
            throw new LoginException("帐号密码错误");
        } else {
            return new R<>(jsonEntity.getBody().get("user"));
        }
    }


    /**
     * 医生端待处理端口
     * @param request
     * @param doctorId
     * @return
     */
    @GetMapping("/pendingOrder")
    public R<List> pendingOrder(HttpServletRequest request, String doctorId) {
        String time = request.getHeader("timeStamp");
        return new R<>(doctorOperationService.pendingOrder(time, doctorId));
    }

    /**
     * 医生端接单操作
     * @param orderId
     * @return
     */
    @GetMapping("/clinicalReception")
    public R<Boolean> clinicalReception(@RequestParam String orderId) {
        return new R<>(medOfficeVisitService.clinicalReception(orderId));
    }

    /**
     * 医生端已完成问诊接口
     * @return
     */
    @GetMapping("/completeProfile")
    public R<List> completeProfile(String doctorId) {
        return new R<>(doctorOperationService.completeProfile(doctorId));
    }

    /**
     * 医生端药品查询接口
     * @param searchKey
     * @return
     */
    @GetMapping("/searchKey")
    public R<List> searchDrugInfo(String searchKey) {
        return new R<>(doctorOperationService.searchDrugInfo(searchKey));
    }

    /**
     * 医生端查询历史订单
     * @return
     */
    @GetMapping("/historyOrder")
    public R<List> historyOrder(String doctorId, Date beginTime, Date endTime, String patientName, boolean patientType, int type) {
        return new R<>(doctorOperationService.historyOrder(doctorId,beginTime,endTime,patientName,patientType,type));
    }

    /**
     * 医生端病例填写接口
     * @return
     */
    @PostMapping("/saveCaseHistory")
    public R<Boolean> saveCaseHistory(@RequestBody JsonEntity jsonEntity) {
        jsonEntity.getBody();
        SaveCase saveCase = BeanUtil.mapToBean(jsonEntity.getBody(), SaveCase.class, false);
        return new R<>(medOvMedicalRecordService.saveCaseHistory(saveCase));
    }
}
