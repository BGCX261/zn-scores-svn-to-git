package com.zink.ezequiel.Zcores.pro;

import android.os.Bundle;
import android.preference.PreferenceManager;
import com.actionbarsherlock.app.SherlockPreferenceActivity;

import java.lang.Override;

/**
 * Created with IntelliJ IDEA.
 * User: Ezequiel Zink
 * Date: 21/03/13
 * Time: 23:40
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends SherlockPreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);    }
}
