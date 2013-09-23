package com.redclay.fanaddict.models;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceSettings {
	
	public String deviceHardwareId; 
	public String deviceType;
	public String deviceTypeId;
	public boolean isNotificationEnabled;
	public String lastUpdate;
	public String notificationChannel = "";
	public int syncHistoryLength;
	public int syncInterval;
	public String user = null;
	public int userDeviceId;
	public int userDeviceProfiles;
	public int userId;
	


	
	public DeviceSettings(JSONObject deviceSettings) throws JSONException{
		this.deviceHardwareId = deviceSettings.getString("DeviceHardwareId");
		this.deviceType = deviceSettings.getString("DeviceType");
		this.deviceTypeId = deviceSettings.getString("DeviceTypeId"); 
		this.isNotificationEnabled = deviceSettings.getBoolean("IsNotificationEnabled"); 
		this.lastUpdate = deviceSettings.getString("LastUpdated"); 
		this.notificationChannel = deviceSettings.getString("NotificationChannel"); 
		this.syncHistoryLength = deviceSettings.getInt("SyncHistoryLength"); 
		this.syncInterval = deviceSettings.getInt("SyncInterval");
		this.user = deviceSettings.getString("User"); 
		this.userDeviceId = deviceSettings.getInt("UserDeviceId"); 
		//this.userDeviceProfiles = deviceSettings.getInt("UserDeviceProfiles"); 
		this.userId = deviceSettings.getInt("UserId"); 
	}
	
	public String toString(){
		return String.format("Settings[ %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s]", deviceHardwareId, 
				deviceType, deviceTypeId, isNotificationEnabled, lastUpdate, notificationChannel, 
				syncHistoryLength, syncInterval, user, userDeviceId, userDeviceProfiles, userId); 
	}
}
