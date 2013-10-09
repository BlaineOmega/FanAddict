package com.redclay.fanaddict.helpers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.redclay.fanaddict.models.DeviceSettings;
import com.redclay.fanaddict.models.UserProfile;

public class Parser {

	private String feed=null; 
	public DeviceSettings mDeviceSettings;
	public List<UserProfile> mProfileList = new ArrayList<UserProfile>(); 
	public List<Long> mProfileIdList = new ArrayList<Long>(); 
	
	public List<UserProfile> getmProfileList() {
		return mProfileList;
	}

	public void setmProfileList(List<UserProfile> mProfileList) {
		this.mProfileList = mProfileList;
	}

	public DeviceSettings getmDeviceSettings() {
		return mDeviceSettings;
	}

	public void setmDeviceSettings(DeviceSettings mDeviceSettings) {
		this.mDeviceSettings = mDeviceSettings;
	}
	
	public List<Long> getUserProfileIds(List<UserProfile> mProfileList){
		for(UserProfile mProfile : mProfileList){
			if(mProfile.isFollowing()){
				mProfileIdList.add(mProfile.getProviderProfileId());
			}
		}
		return mProfileIdList; 
	}
	
	public Parser(String jsonResponse){
		feed = jsonResponse; 
		try {
			parseFeed(feed);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void parseFeed(String feed) throws JSONException{
		
		JSONObject json = new JSONObject(feed);
		if(json.has("DeviceSettings")){
			parseDeviceSettings(json); 
		}
		if(json.has("UserProfiles")){
			parseUserProfile(json); 
		}
		
	}
	
	private void parseDeviceSettings(JSONObject json) throws JSONException{
		JSONObject settingObject = json.getJSONObject("DeviceSettings"); 
		mDeviceSettings = new DeviceSettings(settingObject); 
		System.out.println("Settings: " + mDeviceSettings.toString());
	}
	
	private void parseUserProfile(JSONObject json) throws JSONException{
		JSONArray profileList = json.getJSONArray("UserProfiles");
		int listCount = profileList.length(); 
		
		for(int i=0; i< listCount; i++){
			JSONObject profileObject = profileList.getJSONObject(i); 
			UserProfile mProfileObject = new UserProfile(profileObject); 
			mProfileList.add(mProfileObject); 
			//System.out.println("Profile: " + mProfileObject.toString());
		}
		//System.out.println("ProfileList: " + mProfileList.toString());
		System.out.println("Profile List Count: " + mProfileList.size());
	}

}
