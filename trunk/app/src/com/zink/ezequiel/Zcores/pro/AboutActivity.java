package com.zink.ezequiel.Zcores.pro;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;

import java.util.ArrayList;


public class AboutActivity extends SherlockActivity implements View.OnClickListener {

     /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        PackageInfo pInfo = null;
        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            TextView versionText = (TextView) findViewById(R.id.versionTextView);
            versionText.setText("v"+pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void onClick(View view) {

    }
}

