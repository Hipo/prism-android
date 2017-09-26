package com.hipo.android.prism;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SampleActivity extends AppCompatActivity {

    private String baseUrl = "https://chroma-preprod.tryprism.com/photos/59a3e450093b0f63d95509c3/kXsl1nSDgiYQfrH.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        String url = Prism.withUrl(baseUrl).width(500).height(900).quality(95).backgroundColor("010101").preserveRatio(true).preMultiplied(true).getUrl();

        ImageView imageView = findViewById(R.id.imageview);
        Picasso.with(this).load(url).into(imageView);

    }

}
