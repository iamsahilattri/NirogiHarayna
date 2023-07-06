package com.nirogi.harayna.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class DistrictModel {


    @Expose
    @SerializedName("distName")
    private String distname;
    @Expose
    @SerializedName("distId")
    private int distid;

    public String getDistname() {
        return distname;
    }

    public void setDistname(String distname) {
        this.distname = distname;
    }

    public int getDistid() {
        return distid;
    }

    public void setDistid(int distid) {
        this.distid = distid;
    }
}
