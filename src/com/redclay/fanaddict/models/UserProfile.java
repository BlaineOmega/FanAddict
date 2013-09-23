package com.redclay.fanaddict.models;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile {
	
	private String description;
	private int gender; 
	private boolean isFollowing; 
	private boolean isOfficial; 
	private String orgName; 
	private int profileId; 
	private String profileName; 
	private int profileType; 
	private int profileTypeId; 
	private String profileTypeName; 
	private int providerId; 
	private String providerName;
	private long providerProfileId; 
	private String providerProfileName; 
	private int sportId;
	private String sportName; 
	private int userId;
	private String userName; 
	
	
	public UserProfile(JSONObject profileObject) throws JSONException{
		this.description = profileObject.getString("Description");
		this.gender = profileObject.getInt("Gender"); 
		this.isFollowing  = profileObject.getBoolean("IsFollowing"); 
		this.isOfficial = profileObject.getBoolean("IsOfficial"); 
		this.orgName = profileObject.getString("OrganizationName");
		this.profileId = profileObject.getInt("ProfileId"); 
		this.profileName = profileObject.getString("ProfileName"); 
		this.profileType = profileObject.getInt("ProfileType"); 
		this.profileTypeId = profileObject.getInt("ProfileTypeId"); 
		this.profileTypeName = profileObject.getString("ProfileTypeName"); 
		this.providerId = profileObject.getInt("ProviderId"); 
		this.providerName = profileObject.getString("ProviderName"); 
		this.providerProfileId = profileObject.getLong("ProviderProfileId"); 
		this.providerProfileName = profileObject.getString("ProviderProfileName"); 
		this.sportId = profileObject.getInt("SportId"); 
		this.sportName = profileObject.getString("SportName");
		this.userId = profileObject.getInt("UserId"); 
		this.userName = profileObject.getString("UserName"); 
	}
	
	
	public String toString(){
		return String.format("Profile[ %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s]", description, gender, isFollowing, isOfficial, orgName, profileId, 
				profileName, profileType, profileTypeId, profileTypeName, providerId, providerName, providerProfileId, providerProfileName, sportId, sportName, userId, userName); 
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public boolean isFollowing() {
		return isFollowing;
	}


	public void setFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}


	public boolean isOfficial() {
		return isOfficial;
	}


	public void setOfficial(boolean isOfficial) {
		this.isOfficial = isOfficial;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public String getProfileName() {
		return profileName;
	}


	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}


	public int getProfileType() {
		return profileType;
	}


	public void setProfileType(int profileType) {
		this.profileType = profileType;
	}


	public int getProfileTypeId() {
		return profileTypeId;
	}


	public void setProfileTypeId(int profileTypeId) {
		this.profileTypeId = profileTypeId;
	}


	public String getProfileTypeName() {
		return profileTypeName;
	}


	public void setProfileTypeName(String profileTypeName) {
		this.profileTypeName = profileTypeName;
	}


	public int getProviderId() {
		return providerId;
	}


	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}


	public String getProviderName() {
		return providerName;
	}


	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public long getProviderProfileId() {
		return providerProfileId;
	}


	public void setProviderProfileId(long providerProfileId) {
		this.providerProfileId = providerProfileId;
	}


	public String getProviderProfileName() {
		return providerProfileName;
	}


	public void setProviderProfileName(String providerProfileName) {
		this.providerProfileName = providerProfileName;
	}


	public int getSportId() {
		return sportId;
	}


	public void setSportId(int sportId) {
		this.sportId = sportId;
	}


	public String getSportName() {
		return sportName;
	}


	public void setSportName(String sportName) {
		this.sportName = sportName;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

}
