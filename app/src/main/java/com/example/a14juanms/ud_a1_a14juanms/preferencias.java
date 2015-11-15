package com.example.a14juanms.ud_a1_a14juanms;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.a14juanms.ud_a1_a14juanms.R;

public class preferencias extends Activity {

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferencias);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFragment()).commit();
    }

}