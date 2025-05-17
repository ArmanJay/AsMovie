package dev.jay.asmovie.activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import dev.jay.asmovie.R;

public class DetailsActivity extends AppCompatActivity {
    private TextView textTitle, textDescription;
    private Button btnPlay;
    private String videoUrl;
    private ImageView poster;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        btnPlay = findViewById(R.id.btnPlay);
        poster = findViewById(R.id.poster);
        Intent intent = getIntent();
        textTitle.setText(intent.getStringExtra("title"));
        textTitle.setTextColor(Color.rgb(255,255,255));
        textDescription.setTextColor(Color.rgb(255,255,255));
        textDescription.setText(intent.getStringExtra("description"));
        Picasso.get().load(intent.getStringExtra("poster")).into(poster);
        videoUrl = intent.getStringExtra("video_url");

        btnPlay.setOnClickListener(v -> {
            Intent playIntent = new Intent(this, VideoPlayerActivity.class);
            playIntent.putExtra("video_url", videoUrl);
            startActivity(playIntent);
        });
    }
}
