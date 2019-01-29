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


        public void setBirthday(int birthday){
        this.birthday = birthday;
    }
        public int getBirthday(){
        return this.birthday;
    }
        public void setSpecialCrowd(int specialCrowd){
        this.specialCrowd = specialCrowd;
    }
        public int getSpecialCrowd(){
        return this.specialCrowd;
    }
        public void setUntowardEffect(int untowardEffect){
        this.untowardEffect = untowardEffect;
    }
        public int getUntowardEffect(){
        return this.untowardEffect;
    }
        public void setAdvice(String advice){
        this.advice = advice;
    }
        public String getAdvice(){
        return this.advice;
    }
        public void setSex(String sex){
        this.sex = sex;
    }
        public String getSex(){
        return this.sex;
    }
        public void setPharmacyInfo(String pharmacyInfo){
        this.pharmacyInfo = pharmacyInfo;
    }
        public String getPharmacyInfo(){
        return this.pharmacyInfo;
    }
        public void setMemberName(String memberName){
        this.memberName = memberName;
    }
        public String getMemberName(){
        return this.memberName;
    }
        public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
        public String getDiagnosis(){
        return this.diagnosis;
    }
        public void setDrugAllergy(String drugAllergy){
        this.drugAllergy = drugAllergy;
    }
        public String getDrugAllergy(){
        return this.drugAllergy;
    }
        public void setHighBloodPressure(int highBloodPressure){
        this.highBloodPressure = highBloodPressure;
    }
        public int getHighBloodPressure(){
        return this.highBloodPressure;
    }
        public void setOtherIllness(String otherIllness){
        this.otherIllness = otherIllness;
    }
        public String getOtherIllness(){
        return this.otherIllness;
    }
        public void setUncomfortableSymptom(String uncomfortableSymptom){
        this.uncomfortableSymptom = uncomfortableSymptom;
    }
        public String getUncomfortableSymptom(){
        return this.uncomfortableSymptom;
    }
        public void setMedList(List<MedList> medList){
        this.medList = medList;
    }
        public List<MedList> getMedList(){
        return this.medList;
    }
        public void setChiefComplaint(String chiefComplaint){
        this.chiefComplaint = chiefComplaint;
    }
        public String getChiefComplaint(){
        return this.chiefComplaint;
    }
        public void setRecordid(String recordid){
        this.recordid = recordid;
    }
        public String getRecordid(){
        return this.recordid;
    }
        public void setPresentIllness(String presentIllness){
        this.presentIllness = presentIllness;
    }
        public String getPresentIllness(){
        return this.presentIllness;
    }
        public void setVisitid(String visitid){
        this.visitid = visitid;
    }
        public String getVisitid(){
        return this.visitid;
    }
        public void setCardiopathy(int cardiopathy){
        this.cardiopathy = cardiopathy;
    }
        public int getCardiopathy(){
        return this.cardiopathy;
    }
        public void setDepartment(String department){
        this.department = department;
    }
        public String getDepartment(){
        return this.department;
    }
        public void setDiabetes(int diabetes){
        this.diabetes = diabetes;
    }
        public int getDiabetes(){
        return this.diabetes;
    }
}
