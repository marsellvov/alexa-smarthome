package com.vshc;

public class Payload {
    private String accessToken;
    private String switchControlAction;
    private Appliance appliance;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSwitchControlAction() {
        return switchControlAction;
    }

    public void setSwitchControlAction(String switchControlAction) {
        this.switchControlAction = switchControlAction;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }
}
