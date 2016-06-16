package com.codepath.flixster2;

import android.content.Context;
import android.content.res.Configuration;
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

    //View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;

    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        ViewHolder viewHolder; //view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        if (isLandscape) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop().placeholder(R.drawable.poster_placeholder_land).into(viewHolder.ivImage);
        } else {
            Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop().placeholder(R.drawable.poster_placeholder).into(viewHolder.ivImage);
        }
        // Return the completed view to render on screen
        return convertView;
    }

}

