package com.example.codelabsmaterialme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView sportsTitle;
    private ImageView sportsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sportsTitle = findViewById(R.id.title_detail);
        sportsImage = findViewById(R.id.sport_image_detail);

        sportsTitle.setText(getIntent().getStringExtra("title"));

        Glide.with(this).load(getIntent().
                getIntExtra("image_resource", 0)).into(sportsImage);
    }
}
