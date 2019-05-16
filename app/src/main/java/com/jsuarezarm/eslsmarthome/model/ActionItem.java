package com.jsuarezarm.eslsmarthome.model;

public class ActionItem {

    private String deviceId;
    private String deviceName;
    private String actionId;
    private String actionName;

    public ActionItem(String deviceId, String deviceName, String actionId, String actionName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.actionId = actionId;
        this.actionName =  actionName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
