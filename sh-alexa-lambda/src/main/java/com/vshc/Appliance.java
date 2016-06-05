package com.vshc;


import java.util.Map;

public class Appliance {
    private String applianceId;
    private Map<String, String> additionalApplianceDetails;

    public Map<String, String> getAdditionalApplianceDetails() {
        return additionalApplianceDetails;
    }

    public void setAdditionalApplianceDetails(Map<String, String> additionalApplianceDetails) {
        this.additionalApplianceDetails = additionalApplianceDetails;
    }

    public String getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }
}
