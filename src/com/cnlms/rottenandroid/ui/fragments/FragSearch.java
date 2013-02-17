package com.cnlms.rottenandroid.ui.fragments;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cnlms.rottenandroid.R;
import com.cnlms.rottenandroid.model.Movie;
import com.cnlms.rottenandroid.network.BaseAsyncTask;
import com.cnlms.rottenandroid.network.ListRequest;
import com.cnlms.rottenandroid.network.SearchRequest;
import com.cnlms.rottenandroid.ui.activities.DetailActivity;
import com.cnlms.rottenandroid.ui.adapters.MyListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 12/19/12 2:32 PM
 */
public final class FragSearch extends Fragment {

	private BaseAsyncTask.RequestListener requestListener;
	ListView listView = null;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         *
         *  TO BE IMPLEMENTED
         *
         *  This is where you should return a view for this fragment.
         *
         *  Tip : inflate the view using 'inflater' parameter and a predefined layout
         *
         *
         */
    	View view = inflater.inflate(R.layout.fragment_search, null);
    	Button search_but = (Button) view.findViewById(R.id.button1);
    	search_but.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText text = (EditText) getActivity().findViewById(R.id.editText1);
				String query = text.getText().toString();
				
				requestListener = new BaseAsyncTask.RequestListener() {
		            @Override
		            public void onSuccess(BaseAsyncTask request) {
		            	SearchRequest req = (SearchRequest) request;
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
		            	
		                Toast.makeText(getActivity(), "List Request Succeeded!", Toast.LENGTH_SHORT).show();
		            }

		            @Override
		            public void onFailure(BaseAsyncTask request) {

		               
		                Toast.makeText(getActivity(), "List Request Failed!", Toast.LENGTH_SHORT).show();

		            }

		        };
		        SearchRequest request = new SearchRequest(getActivity(), requestListener);
		        request.addQueryParameter("q", query);
		        request.execute(null,null,null);
			}
    		
    	});
    	
    	return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    private void generateList(List<Movie> list){
    	Log.d("generateList","In generateList");
    	listView = (ListView) getActivity().findViewById(R.id.listview_search);
    	Activity main_act = getActivity();
        MyListAdapter adapter = new MyListAdapter(main_act, R.layout.list_item, list);
        listView.setAdapter(adapter);
        Log.d("generateList","Out generateList");
    }
}
