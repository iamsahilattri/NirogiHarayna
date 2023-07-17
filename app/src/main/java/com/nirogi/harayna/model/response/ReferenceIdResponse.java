package com.nirogi.harayna.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReferenceIdResponse implements Serializable {


    @Expose
    @SerializedName("createdBy")
    private String createdby;
    @Expose
    @SerializedName("createdDate")
    private String createddate;
    @Expose
    @SerializedName("milestones")
    private String milestones;
    @Expose
    @SerializedName("historyDeworming")
    private String historydeworming;
    @Expose
    @SerializedName("contactWithTB")
    private String contactwithtb;
    @Expose
    @SerializedName("historyFeeding")
    private String historyfeeding;
    @Expose
    @SerializedName("familyHistoryIfYes")
    private String familyhistoryifyes;
    @Expose
    @SerializedName("immunStatus")
    private String immunstatus;
    @Expose
    @SerializedName("historyFamily")
    private String historyfamily;
    @Expose
    @SerializedName("histroyAdmIllness")
    private String histroyadmillness;
    @Expose
    @SerializedName("cryAfterBirth")
    private String cryafterbirth;
    @Expose
    @SerializedName("deliveryType")
    private String deliverytype;
    @Expose
    @SerializedName("referenceId")
    private String referenceid;
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("userId")
    private String userid;
    @Expose
    @SerializedName("facility")
    private String facility;
    @Expose
    @SerializedName("district")
    private String district;
    @Expose
    @SerializedName("patientId")
    private String patientid;
    @Expose
    @SerializedName("id")
    private int id;

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getMilestones() {
        return milestones;
    }

    public void setMilestones(String milestones) {
        this.milestones = milestones;
    }

    public String getHistorydeworming() {
        return historydeworming;
    }

    public void setHistorydeworming(String historydeworming) {
        this.historydeworming = historydeworming;
    }

    public String getContactwithtb() {
        return contactwithtb;
    }

    public void setContactwithtb(String contactwithtb) {
        this.contactwithtb = contactwithtb;
    }

    public String getHistoryfeeding() {
        return historyfeeding;
    }

    public void setHistoryfeeding(String historyfeeding) {
        this.historyfeeding = historyfeeding;
    }

    public String getFamilyhistoryifyes() {
        return familyhistoryifyes;
    }

    public void setFamilyhistoryifyes(String familyhistoryifyes) {
        this.familyhistoryifyes = familyhistoryifyes;
    }

    public String getImmunstatus() {
        return immunstatus;
    }

    public void setImmunstatus(String immunstatus) {
        this.immunstatus = immunstatus;
    }

    public String getHistoryfamily() {
        return historyfamily;
    }

    public void setHistoryfamily(String historyfamily) {
        this.historyfamily = historyfamily;
    }

    public String getHistroyadmillness() {
        return histroyadmillness;
    }

    public void setHistroyadmillness(String histroyadmillness) {
        this.histroyadmillness = histroyadmillness;
    }

    public String getCryafterbirth() {
        return cryafterbirth;
    }

    public void setCryafterbirth(String cryafterbirth) {
        this.cryafterbirth = cryafterbirth;
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
    }

    public String getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(String referenceid) {
        this.referenceid = referenceid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
