package com.zink.ezequiel.Zcores.pro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListActivity;
import android.os.Bundle;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import java.util.ArrayList;


public class HomeActivity extends SherlockListActivity implements View.OnClickListener {

    private Adapter adapter;
    private ImageButton convBtn;
    private EditText inputText;
    private Params calcParams;

    class Params {
        public float idealScore;
        public float reqPercentage;
        public float maxApprob;
        public float minApprob;
        public int dec;
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.adapter = new Adapter(this,R.layout.item, new ArrayList<Model>());
        setListAdapter(this.adapter);

        this.convBtn = (ImageButton) findViewById(R.id.calcButton);
        this.inputText = (EditText) findViewById(R.id.editText);
        this.inputText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        onClick(v);
                        return true;
                    }
                }
                return false;
            }
        });
        this.calcParams = this.loadPreferences();
        this.convBtn.setOnClickListener(this);
        // disable the home button and the up affordance:
        getSupportActionBar().setHomeButtonEnabled(false);
        getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }
    private Params loadPreferences () {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Params res = new Params();
        res.dec = sharedPrefs.getInt("pref_key_n_dec", 2);
        res.idealScore = Float.parseFloat(sharedPrefs.getString("pref_key_ideal_score", "100.0f"));
        res.maxApprob = Float.parseFloat(sharedPrefs.getString("pref_key_max_approb", "10.0f"));
        res.minApprob = Float.parseFloat(sharedPrefs.getString("pref_key_min_approb", "4.0f"));
        res.reqPercentage = Float.parseFloat(sharedPrefs.getString("pref_key_req_per", "70.0f"));
        return res;
    }
    @Override
    public void onClick(View view) {
        Model aux = new Model();
        String text = this.inputText.getText().toString();
        if (!text.isEmpty()) {
            aux.setScore(Float.valueOf(text), this.calcParams.dec, this.calcParams.idealScore,
                    this.calcParams.maxApprob, this.calcParams.minApprob, this.calcParams.reqPercentage);
            this.adapter.add(aux);
            this.inputText.setText("");
            this.getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.clear_button:
                // clear list and input text
                this.inputText.setText("");
                this.adapter.clear();
                return true;
            case R.id.settings_button:
                // goto settings screen
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.about_button:
                // goto settings screen
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.close_button:
                moveTaskToBack(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

