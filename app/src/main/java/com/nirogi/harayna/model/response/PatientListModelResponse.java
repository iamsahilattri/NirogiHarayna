package com.nirogi.harayna.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class PatientListModelResponse implements Serializable {


    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("mobileNo")
    private String mobileno;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("age")
    private String age;
    @Expose
    @SerializedName("flagCheck")
    private String flagcheck;
    @Expose
    @SerializedName("income")
    private int income;
    @Expose
    @SerializedName("district")
    private String district;
    @Expose
    @SerializedName("wardVillage")
    private String wardvillage;
    @Expose
    @SerializedName("blockTownCity")
    private String blocktowncity;
    @Expose
    @SerializedName("fatherHusbandLastname")
    private String fatherhusbandlastname;
    @Expose
    @SerializedName("fatherHusbandFirstname")
    private String fatherhusbandfirstname;
    @Expose
    @SerializedName("lastname")
    private String lastname;
    @Expose
    @SerializedName("firstname")
    private String firstname;
    @Expose
    @SerializedName("memberId")
    private String memberid;
    @Expose
    @SerializedName("pppId")
    private String pppid;
    @Expose
    @SerializedName("id")
    private int id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFlagcheck() {
        return flagcheck;
    }

    public void setFlagcheck(String flagcheck) {
        this.flagcheck = flagcheck;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWardvillage() {
        return wardvillage;
    }

    public void setWardvillage(String wardvillage) {
        this.wardvillage = wardvillage;
    }

    public String getBlocktowncity() {
        return blocktowncity;
    }

    public void setBlocktowncity(String blocktowncity) {
        this.blocktowncity = blocktowncity;
    }

    public String getFatherhusbandlastname() {
        return fatherhusbandlastname;
    }

    public void setFatherhusbandlastname(String fatherhusbandlastname) {
        this.fatherhusbandlastname = fatherhusbandlastname;
    }

    public String getFatherhusbandfirstname() {
        return fatherhusbandfirstname;
    }

    public void setFatherhusbandfirstname(String fatherhusbandfirstname) {
        this.fatherhusbandfirstname = fatherhusbandfirstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getPppid() {
        return pppid;
    }

    public void setPppid(String pppid) {
        this.pppid = pppid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
