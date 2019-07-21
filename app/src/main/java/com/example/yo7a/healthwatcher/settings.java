package com.example.yo7a.healthwatcher;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class settings extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		//Getting Preferences
		AppPreferences appPrefs;
		appPrefs = new AppPreferences(getApplicationContext());
		
		//Filling values with saved preferences
		EditText message = (EditText) findViewById(R.id.customMessage);
		EditText contactOne = (EditText) findViewById(R.id.contactOne);
		EditText contactTwo = (EditText) findViewById(R.id.contactTwo);
		EditText contactThree = (EditText) findViewById(R.id.contactThree);
		EditText contactFour = (EditText) findViewById(R.id.contactFour);
		EditText contactFive = (EditText) findViewById(R.id.contactFive);
		
		message.setText(appPrefs.getPrefs("message"));
		contactOne.setText(appPrefs.getPrefs("contact1"));
		contactTwo.setText(appPrefs.getPrefs("contact2"));
		contactThree.setText(appPrefs.getPrefs("contact3"));
		contactFour.setText(appPrefs.getPrefs("contact4"));
		contactFive.setText(appPrefs.getPrefs("contact5"));

		
		Button saveSettings = (Button) findViewById(R.id.saveButton);
		saveSettings.setOnClickListener(saveSettingsListener);
	}

	private OnClickListener saveSettingsListener = new OnClickListener() {
		public void onClick(View v) {
			// Getting values entered in each fields..
			EditText message = (EditText) findViewById(R.id.customMessage);
			EditText contactOne = (EditText) findViewById(R.id.contactOne);
			EditText contactTwo = (EditText) findViewById(R.id.contactTwo);
			EditText contactThree = (EditText) findViewById(R.id.contactThree);
			EditText contactFour = (EditText) findViewById(R.id.contactFour);
			EditText contactFive = (EditText) findViewById(R.id.contactFive);
			
			String customMessage = message.getText().toString();
			String contactOneValue = contactOne.getText().toString();
			String contactTwoValue = contactTwo.getText().toString();
			String contactThreeValue = contactThree.getText().toString();
			String contactFourValue = contactFour.getText().toString();
			String contactFiveValue = contactFive.getText().toString();
			

			// Saving in Preferences..
			AppPreferences appPrefs;
			appPrefs = new AppPreferences(getApplicationContext());
			appPrefs.savePrefs(customMessage, contactOneValue, contactTwoValue,
					contactThreeValue, contactFourValue, contactFiveValue);
			
			Toast.makeText(getBaseContext(), "Settings saved!",
					Toast.LENGTH_LONG).show();
		}
	};

	@Override
	public void onBackPressed() {
		Intent i = new Intent(settings.this, Primary.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}


}