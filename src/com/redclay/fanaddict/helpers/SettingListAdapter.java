package com.redclay.fanaddict.helpers;

import java.util.List;
import com.redclay.fanaddict.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ProfileHolder(); 
            holder.title = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder); 
		}
		else{
			holder = (ProfileHolder)row.getTag(); 
		}
		
		UserProfile profile = profileObject.get(position);
		holder.title.setText(profile.getProviderProfileName()); 
		return row; 
	}
	
	
	static class ProfileHolder{
		TextView title; 
	}
}
