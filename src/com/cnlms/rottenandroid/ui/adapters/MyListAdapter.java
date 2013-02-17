package com.cnlms.rottenandroid.ui.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cnlms.rottenandroid.R;
import com.cnlms.rottenandroid.model.Movie;

public class MyListAdapter extends ArrayAdapter<Movie>{
 	    private Activity parentActivity;
 	    private int itemLayoutId;
 	    private List<Movie> dataSource;
 	    private LayoutInflater inflater;

 		// constructor for adapter
     	    public MyListAdapter(Activity activity, int layoutId, List<Movie> ds){
     	    super(activity, layoutId, ds);
     		parentActivity = activity;
     		itemLayoutId = layoutId;
     		dataSource = ds;
     		inflater = (LayoutInflater) activity.getBaseContext().
     				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     	    
     	    }

     	    
     	@Override
     	public View getView(int pos, View convertView, ViewGroup parentView){
     		View view = convertView;
     		if(convertView == null){
     			view = inflater.inflate(itemLayoutId, parentView, false);
     			ViewHolder holder = new ViewHolder();
     			holder.title_id = (TextView)view.findViewById(R.id.app_list_item_title);
     			holder.id = (TextView) view.findViewById(R.id.app_list_item_id);
     			holder.rating = (TextView) view.findViewById(R.id.app_list_item_rating);
     			view.setTag(holder);
     		}
     		Movie mov = dataSource.get(pos);
     		if(mov != null){
     			ViewHolder holder = (ViewHolder) view.getTag();
     			String data = mov.getTitle();
     			holder.title_id.setText(data);
     			holder.id.setText(mov.getId()+"");
     			holder.rating.setText(mov.getRating()+"");
     		}
     		return view;
     	}
     	    
		@Override
		public int getCount() {
			Log.d("getCount",dataSource.size()+"");
			return (dataSource != null) ? dataSource.size() : 0;
		}

		@Override
		public Movie getItem(int position) {
			return (dataSource != null) ? dataSource.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			Log.d("getItemId",position+"");
			// TODO Auto-generated method stub
			return position;
		}
		
		static class ViewHolder{
			TextView title_id;
			TextView id;
			TextView rating;
		}
}