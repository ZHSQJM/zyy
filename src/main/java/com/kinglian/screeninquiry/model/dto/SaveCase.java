package com.kinglian.screeninquiry.model.dto;

import lombok.Data;

/**
 * @author HXC
 * @date 2019-1-21
 */
@Data
public class SaveCase {

    private String patientName;

    private String patientId;

    private String orderId;

    private String doctorId;

    private String chiefCmplaint;

    private String presentIllness;

    private String drugAllergy;

    private int specialCrowd;

    private int highBloodPressure;

    private int diabetes;

    private int cardiopathy;

    private String otherIllness;

    private String pharmacyInfo;

    private int untowardEffect;

    private String uncomfortableSymptom;

    private  String diagnosis;
}
