package com.example.hellotoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View v){
        Toast.makeText(this,R.string.toast_msg,Toast.LENGTH_LONG).show();
    }

    public void  countUp(View v){
        mCount += 1;
        if(mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}
