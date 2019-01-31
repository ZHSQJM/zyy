package com.kinglian.screeninquiry.service.impl;

import cn.kinglian.spring.util.Query;

import com.kinglian.screeninquiry.dao.*;
import com.kinglian.screeninquiry.model.dto.*;
import com.kinglian.screeninquiry.model.entity.*;
import com.kinglian.screeninquiry.model.entity.User;
import com.kinglian.screeninquiry.utils.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kinglian.screeninquiry.service.DoctorBusinessService;

import com.xiaoleilu.hutool.date.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class DoctorBusinessServiceImpl implements DoctorBusinessService {

    private  final static String Sex="M";


    @Autowired
    private ComDepartmentMapper comDepartmentMapper;


    @Autowired
    private HospitalDoctorExtensionMapper hospitalDoctorExtensionMapper;


    @Autowired
    private ComEmployeeDepartmentMapper comEmployeeDepartmentMapper;


    @Autowired
    private MedOfficeVisitMapper medOfficeVisitMapper;


    @Autowired
    private MedPatientInfoMapper medPatientInfoMapper;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private MedOvMedicalRecordMapper medOvMedicalRecordMapper;



    @Autowired
    WXPayConfigInfo info;

    @Autowired
    CreateParmsCode createParmsCode;



    @Autowired
    private MedOvPresSheetMapper medOvPresSheetMapper;




    @Override
    public Page getDoctorListByDeptId(RequestBaseParam<DoctorDepartmentParam> param, Query<Map> mapQuery)
    {

        Page  listDoctorVtoPage=new Page <>();

        List<DoctorVto> listDoctorVto=new ArrayList<>();

        EntityWrapper<ComEmployeeDepartment> entityComDepartment=new EntityWrapper<>();

        entityComDepartment.eq("department_id",param.getBody().getDoctorDeptId());

        List<ComEmployeeDepartment> listComDeptObjs=comEmployeeDepartmentMapper.selectList(entityComDepartment);

        List<ComEmployeeDepartment> listComDept=comEmployeeDepartmentMapper.selectPage(mapQuery,entityComDepartment);

        for(ComEmployeeDepartment itemCom : listComDept) {
            HospitalDoctorExtension item= hospitalDoctorExtensionMapper.selectById(itemCom.getEmployeeId());

                if (item==null)
                {
                    continue;
                }
                DoctorVto doctorVto = new DoctorVto();
                doctorVto.setDoctorId(item.getId());
                doctorVto.setDoctorImgUrl(item.getPictureUrl());
                doctorVto.setDoctorName(item.getDoctorName());
                doctorVto.setVisitTime("100");
                doctorVto.setDoctorStatus("1");
                doctorVto.setRate("100%");

                List<DoctorDepartmentVto> listDoctorDepartments = new ArrayList<>();


                doctorVto.setListDept(listDoctorDepartments);

                listDoctorVto.add(doctorVto);

            }
        listDoctorVtoPage.setSize(listComDeptObjs.size()/9==0?1:(listComDeptObjs.size()%9>0?listComDeptObjs.size()/9+1:listComDeptObjs.size()/9));
        listDoctorVtoPage.setRecords(listDoctorVto);

        return  listDoctorVtoPage;

    }



    @Override
    public Page getDoctorList(Query<Map> mapQuery)
    {

        Page  listDoctorVtoPage=new Page <>();

        List<DoctorVto> listDoctorVto=new ArrayList<>();

        EntityWrapper<HospitalDoctorExtension> entity=new EntityWrapper<>();

        List <HospitalDoctorExtension> listObjs= hospitalDoctorExtensionMapper.selectList(entity);

        List <HospitalDoctorExtension> list= hospitalDoctorExtensionMapper.selectPage(mapQuery,entity);

        for(HospitalDoctorExtension item : list){
            DoctorVto doctorVto =new DoctorVto();
            doctorVto.setDoctorId(item.getId());
            doctorVto.setDoctorImgUrl(item.getPictureUrl());
            doctorVto.setDoctorName(item.getDoctorName());
            doctorVto.setVisitTime("100");
            doctorVto.setDoctorStatus("1");
            doctorVto.setRate("100%");

            List <DoctorDepartmentVto> listDoctorDepartments=new ArrayList<>();

            EntityWrapper<ComEmployeeDepartment> entityComDepartment=new EntityWrapper<>();

            entity.eq("employee_id",item.getId());

            List<ComEmployeeDepartment> listComDept=comEmployeeDepartmentMapper.selectPage(mapQuery,entityComDepartment);

            for(ComEmployeeDepartment itemCom : listComDept) {

                DoctorDepartmentVto doctorDepartmentVto =new DoctorDepartmentVto();
                ComDepartment comDepartment=comDepartmentMapper.selectById(itemCom.getDepartmentId());
                if (comDepartment!=null) {
                    doctorDepartmentVto.setDoctorDeptId(itemCom.getDepartmentId().toString());
                    doctorDepartmentVto.setDoctorDeptName(comDepartment.getDepartname());

                    if (listDoctorDepartments.contains(doctorDepartmentVto)){
                        continue;
                    }

                    listDoctorDepartments.add(doctorDepartmentVto);
                }

            }
            doctorVto.setListDept(listDoctorDepartments);

            listDoctorVto.add(doctorVto);

        }
        listDoctorVtoPage.setRecords(listDoctorVto);
        listDoctorVtoPage.setSize(listObjs.size()/9==0?1:(listObjs.size()%9>0?listObjs.size()/9+1:listObjs.size()/9));

        return  listDoctorVtoPage;

    }




    @Override
    public Page getDeptList(Query<Map> mapQuery) {
        Page deptPage = new Page<>();
        List<DoctorDepartmentVto> listDeptVto = new ArrayList<>();
        EntityWrapper<ComDepartment> entity = new EntityWrapper<>();
        List<ComDepartment> list = comDepartmentMapper.selectPage(mapQuery, entity);
        for (ComDepartment item : list) {

                DoctorDepartmentVto doctorDepartmentVto = new DoctorDepartmentVto();
                doctorDepartmentVto.setDoctorDeptId(item.getid().toString());
                doctorDepartmentVto.setDoctorDeptName(item.getDepartname());
                listDeptVto.add(doctorDepartmentVto);


        }
        deptPage.setRecords(listDeptVto);
        deptPage.setSize(list.size()/9==0?1:(list.size()%9>0?list.size()/9+1:list.size()/9));
        return deptPage;
    }




    @Override
    public VisitResult submitVisitOrder(RequestBaseParam<SubmitVisitBodyParam> param)  {

        VisitResult r = new VisitResult();
        MedPatientInfo medPatientInfo;


        MedOfficeVisit medOfficeVisit =new MedOfficeVisit();
        medOfficeVisit.setVisitid(UUIDGenerator.generate());
        medOfficeVisit.setCdid(param.getBody().getDoctorId());
        medOfficeVisit.setDeleted(false);

        HospitalDoctorExtension item= hospitalDoctorExtensionMapper.selectById(param.getBody().getDoctorId());
        if (item!=null) {
            medOfficeVisit.setCdName(item.getDoctorName());
            medOfficeVisit.setRegDepartmentId(param.getBody().getDoctorDeptId());

            if (param.getBody().getDoctorDeptId() != null && param.getBody().getDoctorDeptId().equals("0")) {
                ComDepartment comDepartment = comDepartmentMapper.selectById(param.getBody().getDoctorDeptId());
                if (comDepartment != null) {

                    medOfficeVisit.setRegDepartmentName(comDepartment.getDepartname());

                }
            } else

            {


            }

        }
        medOfficeVisit.setCreatedDate(DateTime.now());
        medOfficeVisit.setPatientid(param.getBody().getPortalId());
        medOfficeVisit.setClinicid(90000);
        medOfficeVisit.setBusinessType(2);
        medOfficeVisit.setOrderState(1);
        medOfficeVisit.setVisitStatus("0");
        medOfficeVisit.setPayMode(1);

        Integer i= medOfficeVisitMapper.insert(medOfficeVisit);

         medPatientInfo=medPatientInfoMapper.selectById(param.getBody().getPortalId());

         if (medPatientInfo==null)
         {
             medPatientInfo=new MedPatientInfo();
             medPatientInfo.setId(param.getBody().getPortalId());
             medPatientInfo.setCreatedDate(DateTime.now());
             medPatientInfo.setDeleted(false);
             medPatientInfo.setNewUser(0);
             medPatientInfo.setOpId(param.getBody().getPortalId());


             /**
              * by HXC
              */
             medPatientInfoMapper.insert(medPatientInfo);

             /*EntityWrapper<User> userEntity=new EntityWrapper<>();

             userEntity.eq("user_id",param.getBody().getPortalId());

             Map params = new HashMap();
             params.put("page", 1);

             List<User> listUser=userMapper.selectPage(new Query<>(params),userEntity);

             if (listUser!=null&&listUser.size()>0)
             {
                 User userTemp=listUser.get(0);
                 medPatientInfo.setBirthday(userTemp.getBirthday());
                 medPatientInfo.setSex(userTemp.getSex());
                 medPatientInfo.setMemberName(userTemp.getUserName());
                 medPatientInfo.setPortalId(param.getBody().getPortalId());

                 medPatientInfoMapper.insert(medPatientInfo);
             }*/

         }
         else
         {
             medPatientInfo.setNewUser(1);

             medPatientInfoMapper.updateById(medPatientInfo);
         }

        medOfficeVisit.setPatientDob(medPatientInfo.getBirthday());
        medOfficeVisit.setPatientName(medPatientInfo.getMemberName());
        if (medPatientInfo.getSex()==Sex)
        {
        medOfficeVisit.setPatientSex(1);
        }
       else
        {
            medOfficeVisit.setPatientSex(2);
        }

        if (i==1)
        {
            r.visitId=medOfficeVisit.getVisitid();
            r.doctorId=medOfficeVisit.getCdid();
            r.fee="1";
            r.setOrderStatus("1");


            WeChatParams params =new WeChatParams();
            params.total_fee="1";
            params.attach="1";
            params.body="视频问诊";
            params.out_trade_no=r.visitId;
            try {
                String url= WeixinPay.getCodeUrl(params);

                r.setUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
                r.visitId="";
            }


        }

        return r;
    }

    @Override
    public OrderStateVto getOrderState(RequestBaseParam<SubmitVisitBodyParam> param)  {

        OrderStateVto vto=new OrderStateVto();

        EntityWrapper<MedOfficeVisit> entity=new EntityWrapper<>();
        entity.eq("visitid",param.getBody().getVisitId());
        Map params = new HashMap();
        params.put("page", 1);

        List<MedOfficeVisit> list=medOfficeVisitMapper.selectPage(new Query<>(params),entity);
        if (list!=null&&list.size()>0)
        {
            MedOfficeVisit medOfficeVisit1=list.get(0);

            WeChatParams params1 =new WeChatParams();
            params1.out_trade_no=medOfficeVisit1.getVisitid();

            try {
                String strPayState= WeixinPay.getPayState(params1);
               if  (strPayState.equalsIgnoreCase("SUCCESS")) {
                   vto.setPayState("1");
                   vto.setOrderState("7");
                   medOfficeVisit1.setPayCost(BigDecimal.valueOf(0.01));
                   medOfficeVisit1.setOrderState(7);

                   EntityWrapper<MedOfficeVisit> entityMedOffice=new EntityWrapper<>();
                   entity.eq("visitid",medOfficeVisit1.getVisitid());
                   medOfficeVisitMapper.update(medOfficeVisit1,entity);
               }
               else
               {
                   vto.setPayState("0");
                   vto.setOrderState("1");
               }
            } catch (Exception e) {
                e.printStackTrace();

            }



        }

        return  vto;
    }

    @Override
    public MedVisitInfo queryVisitInfo(RequestBaseParam<SubmitVisitBodyParam> param)  {

      /*  public String doctorId;
        public String doctorName;
        public String doctorStatus;
        public String rate;
        public String visitTime;
        public String doctorImgUrl;
        public List<DoctorDepartmentVto> listDept;*/

        MedVisitInfo medVisitInfo=new MedVisitInfo();
        Map map=new HashMap();
        Map params = new HashMap();
        params.put("visitid", param.getBody().visitId);
        Query<Map> query =new Query<Map>(params);
        List<Map> medicalRecordDetails1 = null;
        try {
            medicalRecordDetails1 = medOvMedicalRecordMapper.getMedicalRecordDetails(query, query.getCondition());
            if (medicalRecordDetails1 != null && medicalRecordDetails1.size() != 0)

            {
                 map = medicalRecordDetails1.get(0);
                java.util.Date birthday = (java.util.Date)map.get("birthday");
                map.put("birthday", GetAge.getAge(birthday));

                EntityWrapper<MedOfficeVisit> entity=new EntityWrapper<>();
                entity.eq("visitid",param.getBody().getVisitId());
                Map params1 = new HashMap();
                params1.put("page", 1);

                List<MedOfficeVisit> list=medOfficeVisitMapper.selectPage(new Query<>(params1),entity);
                if (list!=null&&list.size()>0) {
                    MedOfficeVisit medOfficeVisit1 = list.get(0);
                    if (medOfficeVisit1!=null) {
                        map.put("doctorId", medOfficeVisit1.getCdid());
                        map.put("doctorName", medOfficeVisit1.getCdName());
                        map.put("visitTime", "1000");
                        map.put("rate", "100%");

                        HospitalDoctorExtension item= hospitalDoctorExtensionMapper.selectById(medOfficeVisit1.getCdid());

                        if (item!=null)
                        {
                            map.put("doctorImgUrl", item.getPictureUrl());
                            map.put("hospitalName", "互联网医院");
                        }

                    }

                }
               // medicalRecordDetails1.remove(0);
              //  medicalRecordDetails1.add(map);
                List<String> listTemp = new ArrayList<>();
                // 不需要反射的字段
                listTemp.add("medList");


                ConversionUtils.mapConvertBean(map,medVisitInfo);
            }
            else
            {
                EntityWrapper<MedOfficeVisit> entity=new EntityWrapper<>();
                entity.eq("visitid",param.getBody().getVisitId());
                Map params1 = new HashMap();
                params1.put("page", 1);

                List<MedOfficeVisit> list=medOfficeVisitMapper.selectPage(new Query<>(params1),entity);
                if (list!=null&&list.size()>0) {
                    MedOfficeVisit medOfficeVisit1 = list.get(0);
                    if (medOfficeVisit1!=null) {
                        medVisitInfo.setDoctorId( medOfficeVisit1.getCdid());
                        medVisitInfo.setDoctorName( medOfficeVisit1.getCdName());
                        medVisitInfo.setVisitTime("1000");
                        medVisitInfo.setRate("100%");

                        HospitalDoctorExtension item= hospitalDoctorExtensionMapper.selectById(medOfficeVisit1.getCdid());

                        if (item!=null)
                        {
                            medVisitInfo.setDoctorImgUrl(item.getPictureUrl());
                            medVisitInfo.setHospitalName("互联网医院");
                        }

                    }

                }
                List<MedList> listMed=new ArrayList<>() ;
                medVisitInfo.setMedList(listMed);


            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无数据");
        }


        return  medVisitInfo;
    }


    @Override
    public MedVisitInfo queryVisitInfoByAudit(RequestBaseParam<SubmitVisitBodyParam> param)  {

      /*  public String doctorId;
        public String doctorName;
        public String doctorStatus;
        public String rate;
        public String visitTime;
        public String doctorImgUrl;
        public List<DoctorDepartmentVto> listDept;*/
        MedVisitInfo medVisitInfo=new MedVisitInfo();
        Map map=new HashMap();
        Map params = new HashMap();
        params.put("visitid", param.getBody().visitId);
        Query<Map> query =new Query<Map>(params);
        List<Map> medicalRecordDetails1 = null;
        try {
            medicalRecordDetails1 = medOvMedicalRecordMapper.getMedicalRecordDetails(query, query.getCondition());
            if (medicalRecordDetails1 != null && medicalRecordDetails1.size() != 0){
                map = medicalRecordDetails1.get(0);
                java.util.Date birthday = (java.util.Date)map.get("birthday");
                map.put("birthday", GetAge.getAge(birthday));

                EntityWrapper<MedOvPresSheet> entity=new EntityWrapper<>();
                entity.eq("visitid",param.getBody().getVisitId());
                Map params1 = new HashMap();
                params1.put("page", 1);

                List<MedOvPresSheet> list= medOvPresSheetMapper.selectPage(new Query<>(params1),entity);
                if (list!=null&&list.size()>0) {
                    MedOvPresSheet medOvPresSheet = list.get(0);



                    if (medOvPresSheet!=null) {
                        map.put("doctorId", medOvPresSheet.getAuditDoctorAccount());
                        map.put("doctorName", medOvPresSheet.getAuditDoctorName());
                        map.put("visitTime", "1000");
                        map.put("rate", "100%");

                 /*       HospitalDoctorExtension item= hospitalDoctorExtensionMapper.selectById(medOfficeVisit1.getCdid());*/

                    /*    if (item!=null)
                        {*/
                            map.put("doctorImgUrl", "");
                            map.put("hospitalName", "互联网医院");
                      //  }
                        map.put("presUrl","http://yun-test.kinglian.net/officialWeChat/prescriptionDetail?visitId="+param.getBody().visitId);
                        //微信公众号二维码
                        map.put("qrCodeUrl",createParmsCode.getParmsCode(param.getBody().visitId));
                    }
                    List<String> listTemp = new ArrayList<>();
                    // 不需要反射的字段
                    listTemp.add("medList");
                    listTemp.add("presUrl");

                    ConversionUtils.mapConvertBean(map,medVisitInfo);
                }
                else
                {


                }

            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无数据");
        }


        return  medVisitInfo;
    }
}
