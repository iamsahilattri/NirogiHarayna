package com.nirogi.harayna.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public  class ReferredSurveyDataResponse implements Serializable {



    @Expose
    @SerializedName("Patient")
    public ArrayList<DataPatientEntity> patient;
    @Expose
    @SerializedName("Data")
    public ArrayList<DataOpenEntity> data;

    public ArrayList<DataPatientEntity> getPatient() {
        return patient;
    }

    public void setPatient(ArrayList<DataPatientEntity> patient) {
        this.patient = patient;
    }

    public ArrayList<DataOpenEntity> getData() {
        return data;
    }

    public void setData(ArrayList<DataOpenEntity> data) {
        this.data = data;
    }

    public static class DataPatientEntity implements Serializable {
        @Expose
        @SerializedName("data")
        private DataPaitentEntity data;
        @Expose
        @SerializedName("title")
        private String title;


        public DataPaitentEntity getData() {
            return data;
        }

        public void setData(DataPaitentEntity data) {
            this.data = data;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    public static class DataOpenEntity implements Serializable {
        @Expose
        @SerializedName("data")
        private DataEntity data;
        @Expose
        @SerializedName("title")
        private String title;


        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class DataPaitentEntity implements Serializable{


        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("pppId")
        private String pppId;

        @Expose
        @SerializedName("memberId")
        private String memberId;

        @Expose
        @SerializedName("firstname")
        private String firstname;

        @Expose
        @SerializedName("lastname")
        private String lastname;

        @Expose
        @SerializedName("fatherHusbandFirstname")
        private String fatherHusbandFirstname;


        @Expose
        @SerializedName("fatherHusbandLastname")
        private String fatherHusbandLastname;

        @Expose
        @SerializedName("blockTownCity")
        private String blockTownCity;

        @Expose
        @SerializedName("wardVillage")
        private String wardVillage;

        @Expose
        @SerializedName("district")
        private String district;

        @Expose
        @SerializedName("income")
        private String income;

        @Expose
        @SerializedName("flagCheck")
        private String flagCheck;

        @Expose
        @SerializedName("age")
        private String age;


        @Expose
        @SerializedName("gender")
        private String gender;



        @Expose
        @SerializedName("mobileNo")
        private String mobileNo;



        @Expose
        @SerializedName("address")
        private String address;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPppId() {
            return pppId;
        }

        public void setPppId(String pppId) {
            this.pppId = pppId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getFatherHusbandFirstname() {
            return fatherHusbandFirstname;
        }

        public void setFatherHusbandFirstname(String fatherHusbandFirstname) {
            this.fatherHusbandFirstname = fatherHusbandFirstname;
        }

        public String getFatherHusbandLastname() {
            return fatherHusbandLastname;
        }

        public void setFatherHusbandLastname(String fatherHusbandLastname) {
            this.fatherHusbandLastname = fatherHusbandLastname;
        }

        public String getBlockTownCity() {
            return blockTownCity;
        }

        public void setBlockTownCity(String blockTownCity) {
            this.blockTownCity = blockTownCity;
        }

        public String getWardVillage() {
            return wardVillage;
        }

        public void setWardVillage(String wardVillage) {
            this.wardVillage = wardVillage;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getFlagCheck() {
            return flagCheck;
        }

        public void setFlagCheck(String flagCheck) {
            this.flagCheck = flagCheck;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class DataEntity implements Serializable{




        @Expose
        @SerializedName("walkStairsWithSupport")
        private String walkStairsWithSupport;
        @Expose
        @SerializedName("neckHolding")
        private String neckHolding;

        @Expose
        @SerializedName("socialSmile")
        private String socialSmile;

        @Expose
        @SerializedName("sittingWithSupport")
        private String sittingWithSupport;

        @Expose
        @SerializedName("sittingWithoutSupport")
        private String sittingWithoutSupport;


        @Expose
        @SerializedName("standingWithoutSupport")
        private String standingWithoutSupport;


        @Expose
        @SerializedName("standingWithSupport")
        private String standingWithSupport;

        @Expose
        @SerializedName("pincerGrasp")
        private String pincerGrasp;


        @Expose
        @SerializedName("strangerAnxiety")
        private String strangerAnxiety;


        @Expose
        @SerializedName("bisyllableSpeech")
        private String bisyllableSpeech;


        @Expose
        @SerializedName("walkingWithoutSupport")
        private String walkingWithoutSupport;

        @Expose
        @SerializedName("speakFiveToTenWords")
        private String speakFiveToTenWords;

        @Expose
        @SerializedName("followSimpleDirection")
        private String followSimpleDirection;

        @Expose
        @SerializedName("jumpWithBothFeet")
        private String jumpWithBothFeet;


        @Expose
        @SerializedName("scrabbling")
        private String scrabbling;
        @Expose
        @SerializedName("speakAtleastFiftyWords")
        private String speakAtleastFiftyWords;


        @Expose
        @SerializedName("cappillaryRefillTime")
        private String cappillaryRefillTime;
        @Expose
        @SerializedName("prescription")
        private String prescription;

        @Expose
        @SerializedName("alreadyKnown")
        private String alreadyKnown;

        @Expose
        @SerializedName("diagnosed")
        private String diagnosed;

        @Expose
        @SerializedName("eyeCongenitalCataract")
        private String eyeCongenitalCataract;

        @Expose
        @SerializedName("genitalExam")
        private String genitalExam;


        @Expose
        @SerializedName("breastExam")
        private String breastExam;

        @Expose
        @SerializedName("jointExam")
        private String jointExam;

        @Expose
        @SerializedName("oralExam")
        private String oralExam;

        @Expose
        @SerializedName("movementOfEyeWithLight")
        private String movementOfEyeWithLight;

        @Expose
        @SerializedName("dentalExam")
        private String dentalExam;

        @Expose
        @SerializedName("colorVision")
        private String colorVision;

        @Expose
        @SerializedName("leftEye")
        private String leftEye;


        @Expose
        @SerializedName("rightEye")
        private String rightEye;



        @Expose
        @SerializedName("hearing")
        private String hearing;



        @Expose
        @SerializedName("cns")
        private String cns;




        @Expose
        @SerializedName("perAbdomen")
        private String perAbdomen;



        @Expose
        @SerializedName("cvs")
        private String cvs;
        @Expose
        @SerializedName("chest")
        private String chest;

        @Expose
        @SerializedName("pulseRate")
        private String pulseRate;

        @Expose
        @SerializedName("oxygenSaturation")
        private String oxygenSaturation;

        @Expose
        @SerializedName("bmi")
        private String bmi;

        @Expose
        @SerializedName("pallor")
        private String pallor;


        @Expose
        @SerializedName("jaundice")
        private String jaundice;


        @Expose
        @SerializedName("cyanosis")
        private String cyanosis;

        @Expose
        @SerializedName("respiratoryRate")
        private String respiratoryRate;

        @Expose
        @SerializedName("heartRate")
        private String heartRate;

        @Expose
        @SerializedName("pedalOedema")
        private String pedalOedema;


        @Expose
        @SerializedName("congenitalAnomalies")
        private String congenitalAnomalies;

        @Expose
        @SerializedName("anteriorFontanelle")
        private String anteriorFontanelle;

        @Expose
        @SerializedName("hairTexture")
        private String hairTexture;

        @Expose
        @SerializedName("skinTexture")
        private String skinTexture;


        @Expose
        @SerializedName("skinTurgor")
        private String skinTurgor;


        @Expose
        @SerializedName("skinLesions")
        private String skinLesions;


        @Expose
        @SerializedName("bloodPressure")
        private String bloodPressure;


        @Expose
        @SerializedName("wristWidening")
        private String wristWidening;



        @Expose
        @SerializedName("vitADeficiency")
        private String vitADeficiency;

        @Expose
        @SerializedName("gonadalExam")
        private String gonadalExam;


        @Expose
        @SerializedName("historyEarDischarge")
        private String historyEarDischarge;


        @Expose
        @SerializedName("speech")
        private String speech;

        @Expose
        @SerializedName("iq")
        private String iq;

        @Expose
        @SerializedName("clubbing")
        private String clubbing;

        @Expose
        @SerializedName("lymphadenopathy")
        private String lymphadenopathy;



        @Expose
        @SerializedName("weight")
        private String weight;

        @Expose
        @SerializedName("height")
        private String height;

        @Expose
        @SerializedName("headCircum")
        private String headCircum;
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

        @Expose
        @SerializedName("midArmCircum")
        private String midArmCircum;
        @Expose
        @SerializedName("relevantInvestigation")
        private String relevantInvestigation;

        @Expose
        @SerializedName("hb")
        private String hb;

        public String getRelevantInvestigation() {
            return relevantInvestigation;
        }

        public void setRelevantInvestigation(String relevantInvestigation) {
            this.relevantInvestigation = relevantInvestigation;
        }

        public String getHb() {
            return hb;
        }

        public void setHb(String hb) {
            this.hb = hb;
        }

        public String getMidArmCircum() {
            return midArmCircum;
        }

        public void setMidArmCircum(String midArmCircum) {
            this.midArmCircum = midArmCircum;
        }

        public String getWalkStairsWithSupport() {
            return walkStairsWithSupport;
        }

        public void setWalkStairsWithSupport(String walkStairsWithSupport) {
            this.walkStairsWithSupport = walkStairsWithSupport;
        }

        public String getNeckHolding() {
            return neckHolding;
        }

        public void setNeckHolding(String neckHolding) {
            this.neckHolding = neckHolding;
        }

        public String getSocialSmile() {
            return socialSmile;
        }

        public void setSocialSmile(String socialSmile) {
            this.socialSmile = socialSmile;
        }

        public String getSittingWithSupport() {
            return sittingWithSupport;
        }

        public void setSittingWithSupport(String sittingWithSupport) {
            this.sittingWithSupport = sittingWithSupport;
        }

        public String getSittingWithoutSupport() {
            return sittingWithoutSupport;
        }

        public void setSittingWithoutSupport(String sittingWithoutSupport) {
            this.sittingWithoutSupport = sittingWithoutSupport;
        }

        public String getStandingWithoutSupport() {
            return standingWithoutSupport;
        }

        public void setStandingWithoutSupport(String standingWithoutSupport) {
            this.standingWithoutSupport = standingWithoutSupport;
        }

        public String getStandingWithSupport() {
            return standingWithSupport;
        }

        public void setStandingWithSupport(String standingWithSupport) {
            this.standingWithSupport = standingWithSupport;
        }

        public String getPincerGrasp() {
            return pincerGrasp;
        }

        public void setPincerGrasp(String pincerGrasp) {
            this.pincerGrasp = pincerGrasp;
        }

        public String getStrangerAnxiety() {
            return strangerAnxiety;
        }

        public void setStrangerAnxiety(String strangerAnxiety) {
            this.strangerAnxiety = strangerAnxiety;
        }

        public String getBisyllableSpeech() {
            return bisyllableSpeech;
        }

        public void setBisyllableSpeech(String bisyllableSpeech) {
            this.bisyllableSpeech = bisyllableSpeech;
        }

        public String getWalkingWithoutSupport() {
            return walkingWithoutSupport;
        }

        public void setWalkingWithoutSupport(String walkingWithoutSupport) {
            this.walkingWithoutSupport = walkingWithoutSupport;
        }

        public String getSpeakFiveToTenWords() {
            return speakFiveToTenWords;
        }

        public void setSpeakFiveToTenWords(String speakFiveToTenWords) {
            this.speakFiveToTenWords = speakFiveToTenWords;
        }

        public String getFollowSimpleDirection() {
            return followSimpleDirection;
        }

        public void setFollowSimpleDirection(String followSimpleDirection) {
            this.followSimpleDirection = followSimpleDirection;
        }

        public String getJumpWithBothFeet() {
            return jumpWithBothFeet;
        }

        public void setJumpWithBothFeet(String jumpWithBothFeet) {
            this.jumpWithBothFeet = jumpWithBothFeet;
        }

        public String getScrabbling() {
            return scrabbling;
        }

        public void setScrabbling(String scrabbling) {
            this.scrabbling = scrabbling;
        }

        public String getSpeakAtleastFiftyWords() {
            return speakAtleastFiftyWords;
        }

        public void setSpeakAtleastFiftyWords(String speakAtleastFiftyWords) {
            this.speakAtleastFiftyWords = speakAtleastFiftyWords;
        }

        public String getCappillaryRefillTime() {
            return cappillaryRefillTime;
        }

        public void setCappillaryRefillTime(String cappillaryRefillTime) {
            this.cappillaryRefillTime = cappillaryRefillTime;
        }

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }

        public String getAlreadyKnown() {
            return alreadyKnown;
        }

        public void setAlreadyKnown(String alreadyKnown) {
            this.alreadyKnown = alreadyKnown;
        }

        public String getDiagnosed() {
            return diagnosed;
        }

        public void setDiagnosed(String diagnosed) {
            this.diagnosed = diagnosed;
        }

        public String getEyeCongenitalCataract() {
            return eyeCongenitalCataract;
        }

        public void setEyeCongenitalCataract(String eyeCongenitalCataract) {
            this.eyeCongenitalCataract = eyeCongenitalCataract;
        }

        public String getGenitalExam() {
            return genitalExam;
        }

        public void setGenitalExam(String genitalExam) {
            this.genitalExam = genitalExam;
        }

        public String getBreastExam() {
            return breastExam;
        }

        public void setBreastExam(String breastExam) {
            this.breastExam = breastExam;
        }

        public String getJointExam() {
            return jointExam;
        }

        public void setJointExam(String jointExam) {
            this.jointExam = jointExam;
        }

        public String getOralExam() {
            return oralExam;
        }

        public void setOralExam(String oralExam) {
            this.oralExam = oralExam;
        }

        public String getMovementOfEyeWithLight() {
            return movementOfEyeWithLight;
        }

        public void setMovementOfEyeWithLight(String movementOfEyeWithLight) {
            this.movementOfEyeWithLight = movementOfEyeWithLight;
        }

        public String getDentalExam() {
            return dentalExam;
        }

        public void setDentalExam(String dentalExam) {
            this.dentalExam = dentalExam;
        }

        public String getColorVision() {
            return colorVision;
        }

        public void setColorVision(String colorVision) {
            this.colorVision = colorVision;
        }

        public String getLeftEye() {
            return leftEye;
        }

        public void setLeftEye(String leftEye) {
            this.leftEye = leftEye;
        }

        public String getRightEye() {
            return rightEye;
        }

        public void setRightEye(String rightEye) {
            this.rightEye = rightEye;
        }

        public String getHearing() {
            return hearing;
        }

        public void setHearing(String hearing) {
            this.hearing = hearing;
        }

        public String getCns() {
            return cns;
        }

        public void setCns(String cns) {
            this.cns = cns;
        }

        public String getPerAbdomen() {
            return perAbdomen;
        }

        public void setPerAbdomen(String perAbdomen) {
            this.perAbdomen = perAbdomen;
        }

        public String getCvs() {
            return cvs;
        }

        public void setCvs(String cvs) {
            this.cvs = cvs;
        }

        public String getChest() {
            return chest;
        }

        public void setChest(String chest) {
            this.chest = chest;
        }

        public String getPulseRate() {
            return pulseRate;
        }

        public void setPulseRate(String pulseRate) {
            this.pulseRate = pulseRate;
        }

        public String getOxygenSaturation() {
            return oxygenSaturation;
        }

        public void setOxygenSaturation(String oxygenSaturation) {
            this.oxygenSaturation = oxygenSaturation;
        }

        public String getBmi() {
            return bmi;
        }

        public void setBmi(String bmi) {
            this.bmi = bmi;
        }

        public String getPallor() {
            return pallor;
        }

        public void setPallor(String pallor) {
            this.pallor = pallor;
        }

        public String getJaundice() {
            return jaundice;
        }

        public void setJaundice(String jaundice) {
            this.jaundice = jaundice;
        }

        public String getCyanosis() {
            return cyanosis;
        }

        public void setCyanosis(String cyanosis) {
            this.cyanosis = cyanosis;
        }

        public String getRespiratoryRate() {
            return respiratoryRate;
        }

        public void setRespiratoryRate(String respiratoryRate) {
            this.respiratoryRate = respiratoryRate;
        }

        public String getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(String heartRate) {
            this.heartRate = heartRate;
        }

        public String getPedalOedema() {
            return pedalOedema;
        }

        public void setPedalOedema(String pedalOedema) {
            this.pedalOedema = pedalOedema;
        }

        public String getCongenitalAnomalies() {
            return congenitalAnomalies;
        }

        public void setCongenitalAnomalies(String congenitalAnomalies) {
            this.congenitalAnomalies = congenitalAnomalies;
        }

        public String getAnteriorFontanelle() {
            return anteriorFontanelle;
        }

        public void setAnteriorFontanelle(String anteriorFontanelle) {
            this.anteriorFontanelle = anteriorFontanelle;
        }

        public String getHairTexture() {
            return hairTexture;
        }

        public void setHairTexture(String hairTexture) {
            this.hairTexture = hairTexture;
        }

        public String getSkinTexture() {
            return skinTexture;
        }

        public void setSkinTexture(String skinTexture) {
            this.skinTexture = skinTexture;
        }

        public String getSkinTurgor() {
            return skinTurgor;
        }

        public void setSkinTurgor(String skinTurgor) {
            this.skinTurgor = skinTurgor;
        }

        public String getSkinLesions() {
            return skinLesions;
        }

        public void setSkinLesions(String skinLesions) {
            this.skinLesions = skinLesions;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public String getWristWidening() {
            return wristWidening;
        }

        public void setWristWidening(String wristWidening) {
            this.wristWidening = wristWidening;
        }

        public String getVitADeficiency() {
            return vitADeficiency;
        }

        public void setVitADeficiency(String vitADeficiency) {
            this.vitADeficiency = vitADeficiency;
        }

        public String getGonadalExam() {
            return gonadalExam;
        }

        public void setGonadalExam(String gonadalExam) {
            this.gonadalExam = gonadalExam;
        }

        public String getHistoryEarDischarge() {
            return historyEarDischarge;
        }

        public void setHistoryEarDischarge(String historyEarDischarge) {
            this.historyEarDischarge = historyEarDischarge;
        }

        public String getSpeech() {
            return speech;
        }

        public void setSpeech(String speech) {
            this.speech = speech;
        }

        public String getIq() {
            return iq;
        }

        public void setIq(String iq) {
            this.iq = iq;
        }

        public String getClubbing() {
            return clubbing;
        }

        public void setClubbing(String clubbing) {
            this.clubbing = clubbing;
        }

        public String getLymphadenopathy() {
            return lymphadenopathy;
        }

        public void setLymphadenopathy(String lymphadenopathy) {
            this.lymphadenopathy = lymphadenopathy;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHeadCircum() {
            return headCircum;
        }

        public void setHeadCircum(String headCircum) {
            this.headCircum = headCircum;
        }

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
}
