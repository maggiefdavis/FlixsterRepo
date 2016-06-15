package com.codepath.flixster2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mfdavis on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }

        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        ivPoster.setImageResource(0); //clear out image from convertview

        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
       // String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
       Picasso.with(getContext()).load(movie.getPosterPath()).into(ivPoster);

      //  Log.d("MoviesAdapter", "Position: " + position);

        // Return the completed view to render on screen
        return convertView;
    }
}

