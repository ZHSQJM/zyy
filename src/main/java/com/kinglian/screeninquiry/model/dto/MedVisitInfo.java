package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class MedVisitInfo {

        private int birthday;

        private int specialCrowd;

        private int untowardEffect;

        private String advice;

        private String sex;

        private String pharmacyInfo;

        private String memberName;

        private String diagnosis;

        private String drugAllergy;

        private int highBloodPressure;

        private String otherIllness;

        private String uncomfortableSymptom;

        private List<MedList> medList;

        private String chiefComplaint;

        private String recordid;

        private String presentIllness;

        private String visitid;

        private int cardiopathy;

        private String department;

        private int diabetes;


        private String doctorId;

       private String doctorName;

       private String visitTime;

       private String rate;

      private String doctorImgUrl;


     private String hospitalName;

}
