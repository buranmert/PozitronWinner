package com.cnlms.rottenandroid.ui.fragments;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cnlms.rottenandroid.R;

import com.cnlms.rottenandroid.network.BaseAsyncTask;
import com.cnlms.rottenandroid.network.ListRequest;
import com.cnlms.rottenandroid.ui.activities.DetailActivity;
import com.cnlms.rottenandroid.ui.adapters.MyListAdapter;
//import com.cnlms.rottenandroid.network.TestRequest;

import com.cnlms.rottenandroid.model.Movie;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 12/19/12 2:31 PM
 */
public final class FragInTheaterMovies extends ListFragment  {

    private BaseAsyncTask.RequestListener requestListener;
    private ListView listView=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	
    	View my_view = inflater.inflate(R.layout.fragment_theater, null);

        requestListener = new BaseAsyncTask.RequestListener() {

            @Override
            public void onSuccess(BaseAsyncTask request) {

                /**
                 *  What my app should do when my request succeeds?
                 *
                 *  In your requests, you can access the data fetched over the network,
                 *  e.g : ((TestRequest)request).getData();
                 *
                 *  DON'T FORGET TO CAST TO YOUR OWN REQUEST!
                 */
            	ListRequest req = (ListRequest) request;
            	//loadList(req.getMovieList());
            	Log.d("MovieList!!!",req.getMovieList().get(0).getTitle());
            	generateList(req.getMovieList());
            	
            	listView.setOnItemClickListener(new OnItemClickListener(){

        			@Override
        			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
        					long id) {
        				Movie mov = (Movie) listView.getItemAtPosition(pos);
        				Intent intent = new Intent(getActivity(), DetailActivity.class);
        				intent.putExtra("title", mov.getTitle());
        				intent.putExtra("id", mov.getId());
        				intent.putExtra("rating",mov.getRating());
        				intent.putExtra("thumbnail", mov.getImage());
        				intent.putExtra("synopsis", mov.getSynopsis());
        				intent.putExtra("imdb", mov.getIMDB());
        				Map<String,String> cast_map = mov.getCast();
        				intent.putExtra("cast", (Serializable) cast_map);
        				startActivity(intent);
        			}
                	
                });
            	
                Toast.makeText(getActivity(), "Test Request Succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(BaseAsyncTask request) {

                /**
                 *  What my app should do when my request fails?
                 */
                Toast.makeText(getActivity(), "Test Request Failed!", Toast.LENGTH_SHORT).show();

            }

        };
        
        ListRequest request = new ListRequest(getActivity(), requestListener);
        request.execute(null,null,null);
        
        return my_view;
        //return super.onCreateView(inflater, container, savedInstanceState);
        
    }
    
    private void generateList(List<Movie> list){
    	Log.d("generateList","In generateList");
    	listView = (ListView) getActivity().findViewById(android.R.id.list);
    	Activity main_act = getActivity();
        MyListAdapter adapter = new MyListAdapter(main_act, R.layout.list_item, list);
        listView.setAdapter(adapter);
        Log.d("generateList","Out generateList");
    }
}
