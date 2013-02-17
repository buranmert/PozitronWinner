package com.cnlms.rottenandroid.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import org.json.JSONObject;
import com.cnlms.rottenandroid.model.Movie;

import org.json.JSONArray;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 12/20/12 5:31 AM
 */
public class ListRequest extends BaseAsyncTask {

    private List<Movie> movie_list;

    private String data;

    public ListRequest(Context context, RequestListener listener) {
        super(context, listener);
    }


    @Override
    protected String getRequestEndpointUrl() {
        return Endpoints.URL_IN_THEATER_MOVIES;
    }

    /**
     *
     *  This method should be overridden in your request classes.
     *
     *  This is where you should parse the JSON returned from the API,
     *  initialize your model classes and add them to a collection or list.
     *
     */
    @Override
    protected void processReceivedData(JSONObject json) throws Exception {

        /**
         *
         *  Parse json object and initialize model classes fetched from the endpoint
         *
         *  Ex :
         *
         *  Movie movie = new Movie();
         *
         *  movie.setTitle(json.getString("movie_title));
         *  ...
         *  ...
         *
         */
    	//data = json.getString("data");
    	movie_list = new ArrayList<Movie>();
    	JSONArray movies = new JSONArray();
    	movies = (JSONArray) json.getJSONArray("movies");
    	Movie temp_mov = null;
    	for(int i=0;i<movies.length();i++){
    		JSONObject temp = movies.getJSONObject(i);
    		try{
    		Map<String,String> cast = new HashMap<String,String>();
    		JSONArray cast_list = (JSONArray) temp.optJSONArray("abridged_cast");
    		for(int k=0;k<cast_list.length();k++){
    			String name = ((JSONObject) cast_list.optJSONObject(k)).optString("name");
    			JSONArray chars_array = ((JSONObject) cast_list.optJSONObject(k)).optJSONArray("characters");
    			String chars = "";
    			for(int j=0;j<chars_array.length();j++){
    				chars += chars_array.getString(j)+"\n";
    			}
    			cast.put(name, chars);
    		}
    		temp_mov = new Movie(temp.optString("title"), temp.optInt("id"),
    				temp.optString("synopsis"),
    				temp.getJSONObject("ratings").optInt("critics_score"),
    				temp.getJSONObject("posters").optString("thumbnail"), cast, temp.getJSONObject("alternate_ids")
    				.optString("imdb"));
    		}
    		catch(Exception ex)
    		{
    			ex.getCause();
    		}
    		movie_list.add(temp_mov);
    	}
        
    }

    public String getData() {
        return data;
    }
    
    public List<Movie> getMovieList(){
    	return movie_list;
    }
}
