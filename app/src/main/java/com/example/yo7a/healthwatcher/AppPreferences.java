package com.example.yo7a.healthwatcher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
	private static final String APP_SHARED_PREFS = "sosmate_preferences";
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;

	// Preferences
	public AppPreferences(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS,
				Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}

	public String getPrefs(String name) {
		String value="";
		if (name.compareTo("message") == 0) {
			value = appSharedPrefs.getString(name, "I'm in great danger! Please help me! My location is:");
		} else {
			value = appSharedPrefs.getString(name, "");
		}
		return value;
	}

	public void savePrefs(String... prefValues) {
		prefsEditor.putString("message", prefValues[0]);
		prefsEditor.putString("contact1", prefValues[1]);
		prefsEditor.putString("contact2", prefValues[2]);
		prefsEditor.putString("contact3", prefValues[3]);
		prefsEditor.putString("contact4", prefValues[4]);
		prefsEditor.putString("contact5", prefValues[5]);
		prefsEditor.commit();
	}
}