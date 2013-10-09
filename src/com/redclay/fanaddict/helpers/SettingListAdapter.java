package com.redclay.fanaddict.helpers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.redclay.fanaddict.R;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.redclay.fanaddict.models.UserProfile;

public class SettingListAdapter extends ArrayAdapter<UserProfile> {
	
	Context context;
	int layoutResourceId;
	List<UserProfile> profileObject = null; 


	public SettingListAdapter(Context context, int layoutResourceId,
			List<UserProfile> profileObject) {
		super(context, layoutResourceId, profileObject);
		this.layoutResourceId = layoutResourceId; 
		this.context = context;
		this.profileObject = profileObject; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ProfileHolder holder; 
		
		if(row == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ProfileHolder(); 
            holder.checkBox = (CheckBox)row.findViewById(R.id.checkFollowing); 
            holder.gender = (TextView)row.findViewById(R.id.gender);
            holder.sport = (TextView)row.findViewById(R.id.sport);
            
            row.setTag(holder); 
		}
		else{
			holder = (ProfileHolder)row.getTag(); 
		}
		
		final UserProfile profile = profileObject.get(position);
		
		//set gender for user profile listview
		if(profile.getGender() == 1){
			holder.gender.setText("Men's"); 
		}else if (profile.getGender() == 2){
			holder.gender.setText("Women's"); 
		}
		
		//set sport name
		holder.sport.setText(profile.getSportName()); 
		
		if(profile.isFollowing() == true){
			holder.checkBox.setChecked(true);
		}else{
			holder.checkBox.setChecked(false);
		}
		
		final int profileId = profile.getProfileId(); 
		
		holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
				ChangeFollow cf = new ChangeFollow();
				cf.execute(isChecked, profileId); 
				
				if(profile.isFollowing == true){
					profile.isFollowing = false;
				}else{
					profile.isFollowing = true;
				}
			}
		}); 
		return row; 
	}
	
	class ChangeFollow extends AsyncTask<Object, String, String>{
		HttpResponse response = null;
		@Override
		protected String doInBackground(Object... arg0) {
			boolean check = (Boolean) arg0[0]; 
			int id = (Integer) arg0[1];
			
			String url = "http://fanaddictsweb.redcley.com/Services/UserProfileService.svc/2/profile";
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-Type", "application/json");
			
			JSONObject json = new JSONObject();
			try {
				json.put("ProfileId", id);
				
				/*
				 * TODO: assign hardware id dynamically
				 */
				json.put("DeviceHardwareId", "NW58xfxz/w+jCiI3E592degUCL4=");
				json.put("IsFollowing", check);
				
				StringEntity se = new StringEntity(json.toString());
				Log.i("Check Request", "SE: " + json.toString());
				post.setEntity(se);

				response = httpClient.execute(post);
				
				Log.i("Check Response", "Feed: "
						+ response.getStatusLine().getStatusCode());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	

			return null;
		}
		

		protected void onPostExecute(){
			
		}

		
	}
	
	static class ProfileHolder{
		TextView gender; 
		CheckBox checkBox; 
		TextView sport; 
	}
}
