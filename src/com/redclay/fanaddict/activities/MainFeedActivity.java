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

import android.support.v4.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			/*
			 * Here we are going to return the correct type of fragment for each
			 * of the pages
			 */
			
			
			switch (position) {
			case 0:
				Log.d("Position", "Default Postion: " + position);
				return MainListFragment.init(position); 
			case 1:
				Log.d("Position", "Default Postion: " + position);
				return MainListFragment.init(position); 
			case 2:
				Log.d("Position", "Default Postion: " + position);
				return MainListFragment.init(position); 
			default:
				Log.d("Position", "Default Postion: " + position);
				return MainListFragment.init(position); 
			}
				
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
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
	 * A list fragment representing a section of the app, but that simply
	 * displays a list.
	 */
	public static class MainListFragment extends ListFragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */

		int fragNum;
		public static final String ARG_SECTION_NUMBER = "section_number";

		private static MainListFragment init(int val) {
			MainListFragment mfl = new MainListFragment();

			Bundle args = new Bundle();
			args.putInt("val", val);
			mfl.setArguments(args);

			return mfl;
		}

		public MainListFragment() {
		}

		/**
		 * Retrieving this instance's number from its arguments.
		 */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Log.d("Fragment", "Fragment Created for " + getArguments().getInt("val"));
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.setting_fragment,
					container, false);
			
			SettingListAdapter settingAdapter = new SettingListAdapter(
					getActivity().getApplicationContext(),
					R.layout.setting_item_row, MainFeedActivity.mProfileList);
			ListView listView = (ListView) rootView.findViewById(android.R.id.list);
			listView.setAdapter(settingAdapter);
				
			return rootView;
		}	
	}

	class RequestUserFeed extends AsyncTask<String, String, HttpResponse> {
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
				/*
				 * TODO:  Assign deviceHardwareId dynamically.
				 */
				json.put("DeviceHardwareId", "NW58xfxz/w+jCiI3E592degUCL4=");
				json.put("DeviceTypeId", "1");
				StringEntity se = new StringEntity(json.toString());
				Log.i("Feed Request", "SE: " + json.toString());
				post.setEntity(se);

				response = httpClient.execute(post);

				Log.i("Feed Response", "Feed: "
						+ response.getStatusLine().getStatusCode());

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
				Parser parser = new Parser(jsonResponse);
				MainFeedActivity.mDeviceSettings = parser.getmDeviceSettings();
				MainFeedActivity.mProfileList = parser.getmProfileList();
				MainFeedActivity.mProfileIdList = parser
						.getUserProfileIds(MainFeedActivity.mProfileList);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class RequestSocialMediaFeeds extends AsyncTask<List<Long>, String, String> {

		@Override
		protected String doInBackground(List<Long>... arg0) {
			List<Long> mList = arg0[0];
			RequestBatch batch = new RequestBatch();

			return null;
		}

	}
}
