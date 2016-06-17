package com.codepath.flixster2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String originalTitle = getIntent().getStringExtra("original_title");
        String backdropPath = getIntent().getStringExtra("backdrop_path");
        String releaseDate = getIntent().getStringExtra("release_date");
        float rating = (float) getIntent().getIntExtra("rating", 0);

        String overview = getIntent().getStringExtra("overview");

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(originalTitle);

        TextView tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvReleaseDate.setText("Release Date: " + releaseDate);

        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvOverview.setText(overview);

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Picasso.with(this).load(backdropPath).fit().centerInside().placeholder(R.drawable.poster_placeholder_land).into(ivImage);

        RatingBar rbRatingBar = (RatingBar) findViewById(R.id.rbRatingBar);
        rbRatingBar.setIsIndicator(true);
        rbRatingBar.setNumStars(5);
        rbRatingBar.setRating(rating/2);
    }
}
