package com.example.codelabsprojectfour;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Class name for log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Unique tag for the intent replay
    public static final int TEXT_REQUEST = 1;

    private EditText mMessageEditText;
    private TextView mReplayHeadTextView, mReplayTextView;

    //Unique tag required for intent extra
    public static final String EXTRA_MESSAGE =
                        "com.example.codelabsprojectfour.extra.MESSAGE";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mReplayHeadTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("replay_visible", true);
            outState.putString("replay_text", mReplayTextView.getText().toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "_______");
        Log.d(LOG_TAG, "OnCreate");

        mMessageEditText = findViewById(R.id.edit_text_main);
        mReplayHeadTextView = findViewById(R.id.text_header_replay);
        mReplayTextView = findViewById(R.id.text_message_replay);

        if(savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("replay_visible");
            if(isVisible) {
                mReplayHeadTextView.setVisibility(View.VISIBLE);
                mReplayTextView.setText(savedInstanceState.getString("replay_text"));
                mReplayTextView.setVisibility(View.VISIBLE);
            }
        }


    }



    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST) {
            if(resultCode == RESULT_OK){
                String replay = data.getStringExtra(SecondActivity.EXTRA_REPLAY);
                mReplayHeadTextView.setVisibility(View.VISIBLE);
                mReplayTextView.setText(replay);
                mReplayTextView.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");

    }
}
