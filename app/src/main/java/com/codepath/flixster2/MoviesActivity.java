package com.codepath.flixster2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MoviesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    ArrayList<Movie> movies;
    MoviesAdapter adapter;
    ListView lvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Only call setContentView once right at the top
        setContentView(R.layout.activity_movies);
        //Customize action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);
        //Initialize movies
        movies = new ArrayList<Movie>();
        //Get the ListView we want to populate
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        setUpListViewListener();
        //Create ArrayAdapter
        adapter = new MoviesAdapter(this, movies);
        //Associate adapter with the ListView
        if (lvMovies !=null) {
            lvMovies.setAdapter(adapter);
        }
        //Get the actual movies
        fetchMoviesAsync();
        //Get swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        //Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMoviesAsync();
                Log.d("REFRESH", "Refreshed successfully.");
                swipeContainer.setRefreshing(false);
            }
        });
        //Configure refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void fetchMoviesAsync() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    adapter.clear();
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    adapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    private void setUpListViewListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchDetailsView(position);
            }
        });

    }

    private void launchDetailsView(int position) {
        Intent i = new Intent(MoviesActivity.this, DetailsActivity.class);
        Movie movie = adapter.getItem(position);
        i.putExtra("original_title", movie.getOriginalTitle());
        i.putExtra("backdrop_path", movie.getBackdropPath());
        i.putExtra("release_date", movie.getReleaseDate());
        i.putExtra("rating", movie.getRating());
        i.putExtra("overview", movie.getOverview());
        startActivity(i);
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String item = data.getExtras().getString("item");
            int position = data.getExtras().getInt("position");
            todoItems.set(position, item);
            aToDoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }*/

}
