package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class MedVisitInfo {

        private int birthday =0;

        private int specialCrowd=0;

        private int untowardEffect=0;

        private String advice="";

        private String sex="";

        private String pharmacyInfo="";

        private String memberName="";

        private String diagnosis="";

        private String drugAllergy="";

        private int highBloodPressure=0;

        private String otherIllness="";

        private String uncomfortableSymptom="";

        private List<MedList> medList;

        private String chiefComplaint="";

        private String recordid="";

        private String presentIllness="";

        private String visitid="";

        private int cardiopathy=0;

        private String department="";

        private int diabetes=0;


        private String doctorId="";

       private String doctorName="";

       private String visitTime="";

       private String rate;

      private String doctorImgUrl;


     private String hospitalName;

     private String presUrl;

    /**
     * 微信公众号二维码
     */
    private String qrCodeUrl;

    /**
     * 审核状态
     */
    private Integer presState;



}
