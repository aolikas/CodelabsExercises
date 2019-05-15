package com.example.codelabsscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mScore1, mScore2;

    private TextView mTeam1, mTeam2;

    //Tags to be used as the keys in OnSavedInstanceState
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTeam1 = findViewById(R.id.score_1);
        mTeam2 = findViewById(R.id.score_2);

        if(savedInstanceState != null){
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            mTeam1.setText(String.valueOf(mScore1));
            mTeam2.setText(String.valueOf(mScore2));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_memu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }



    public void decreaseScore(View view) {
        switch (view.getId()) {
            case R.id.decrease_team_1:
                mScore1 -= 1;
                mTeam1.setText(String.valueOf(mScore1));
                break;
            case R.id.decrease_team_2:
                mScore2 -= 1;
                mTeam2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void increaseScore(View view) {
        switch (view.getId()) {
            case R.id.increase_team_1:
                mScore1 += 1;
                mTeam1.setText(String.valueOf(mScore1));
                break;
            case R.id.increase_team_2:
                mScore2 += 1;
                mTeam2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}