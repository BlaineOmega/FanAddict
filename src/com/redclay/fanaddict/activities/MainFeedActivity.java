package com.redclay.fanaddict.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.RequestBatch;
import com.redclay.fanaddict.R;
import com.redclay.fanaddict.helpers.Parser;
import com.redclay.fanaddict.helpers.SettingListAdapter;
import com.redclay.fanaddict.models.DeviceSettings;
import com.redclay.fanaddict.models.UserProfile;

public class MainFeedActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private String jsonResponse = null; 
	private static DeviceSettings mDeviceSettings;
	private static List<UserProfile> mProfileList = new ArrayList<UserProfile>(); 
	public static List<Long> mProfileIdList = new ArrayList<Long>(); 
	private final String URL = "http://fanaddictsweb.redcley.com/Services/UserProfileService.svc/2/login";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_feed);

		RequestUserFeed feed = new RequestUserFeed(); 
		feed.execute(URL); 
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_feed, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_main_feed).toUpperCase(l);
			case 1:
				return getString(R.string.title_settings).toUpperCase(l);
			case 2:
				return getString(R.string.title_about).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			int page = getArguments().getInt(ARG_SECTION_NUMBER); 
			switch(page){
			case 1:
				View rootView = inflater.inflate(R.layout.setting_fragment,
						container, false);
				SettingListAdapter settingAdapter = new SettingListAdapter(getActivity().getApplicationContext(), R.layout.setting_item_row, MainFeedActivity.mProfileList);
				ListView listView = (ListView)rootView.findViewById(R.id.listView1);
				listView.setAdapter(settingAdapter); 
				break;
			case 2:
				break;
			case 3:
				break; 
			}
			
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	
	class RequestUserFeed extends AsyncTask<String, String, HttpResponse>{
		HttpResponse response = null; 
		
		@Override
		protected HttpResponse doInBackground(String... uri) {
			HttpClient httpClient = new DefaultHttpClient(); 
			HttpPost post = new HttpPost(uri[0]); 
			post.addHeader("Content-Type", "application/json"); 
			try {
				JSONObject json = new JSONObject(); 
				json.put("UserName", "michigan");
				json.put("Password", "fanaddicts");
				json.put("DeviceHardwareId", "NW58xfxz/w+jCiI3E592degUCL4=");
				json.put("DeviceTypeId", "1");
				StringEntity se = new StringEntity(json.toString()); 
				Log.i("Feed Request", "SE: " + json.toString()); 
				post.setEntity(se);
				
				response = httpClient.execute(post); 
				
				Log.i("Feed Response", "Feed: " + response.getStatusLine().getStatusCode()); 
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			return response;
		}

		@Override
		protected void onPostExecute(HttpResponse result) {
			HttpEntity entity = result.getEntity(); 
			
			try {
				jsonResponse = EntityUtils.toString(entity); 
				System.out.println("Resposne: " + jsonResponse );
				Parser parser = new Parser(jsonResponse); 
				MainFeedActivity.mDeviceSettings = parser.getmDeviceSettings(); 
				MainFeedActivity.mProfileList = parser.getmProfileList(); 
				MainFeedActivity.mProfileIdList = parser.getUserProfileIds(MainFeedActivity.mProfileList);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
	}
	
	class RequestSocialMediaFeeds extends AsyncTask<List<Long>, String, String>{

		@Override
		protected String doInBackground(List<Long>... arg0) {
			List<Long> mList = arg0[0]; 
			RequestBatch batch = new RequestBatch(); 
			
			return null;
		}
		
	}
}
