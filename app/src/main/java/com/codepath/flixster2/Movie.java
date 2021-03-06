package com.codepath.flixster2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mfdavis on 6/15/16.
 */
public class Movie {
    public String originalTitle;
    public String overview;
    public String posterPath;
    public String backdropPath;
    public int rating;
    public String releaseDate;
    public int id;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public int getRating() {
        return rating;
    }

    public String getReleaseDate() { return releaseDate; }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public int getId() { return id; }

    public Movie (JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.rating = jsonObject.getInt("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getInt("id");
    }

    public static ArrayList<Movie> fromJSONArray (JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie (array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
