package com.cnlms.rottenandroid.ui.adapters;

import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cnlms.rottenandroid.R;

public class CastAdapter extends BaseAdapter{
 	    private Activity parentActivity;
 	    private int itemLayoutId;
 	    private Map<String,String> dataSource;
 	    private LayoutInflater inflater;
 	    private Object[] names;

 		// constructor for adapter
     	    public CastAdapter(Activity activity, int layoutId, Map<String,String> ds){
     	    super();
     		parentActivity = activity;
     		itemLayoutId = layoutId;
     		dataSource = ds;
     		inflater = (LayoutInflater) activity.getBaseContext().
     				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     	    names = ds.keySet().toArray();
     	    }

 		@Override
 		public View getView(int pos, View convertView, ViewGroup parentView){
 			View view = convertView;
 			if(convertView == null){
 				view = inflater.inflate(itemLayoutId, parentView, false);
 				ViewHolder holder = new ViewHolder();
 				holder.name = (TextView) view.findViewById(R.id.name);
 				holder.characters = (TextView) view.findViewById(R.id.characters);
 				view.setTag(holder);
 			}
 			String name = (String) names[pos];
 			if(name != null){
 				ViewHolder holder = (ViewHolder) view.getTag();
 				holder.name.setText((String) names[pos]);
 				holder.characters.setText(dataSource.get((String) names[pos]));
 			}
 			return view;
 		}

		@Override
		public int getCount() {
			Log.d("getCount",dataSource.size()+"");
			return (dataSource != null) ? dataSource.size() : 0;
		}

		@Override
		public String getItem(int position) {
			return (dataSource != null) ? dataSource.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			Log.d("getItemId",position+"");
			// TODO Auto-generated method stub
			return position;
		}
		
		static class ViewHolder{
			TextView name;
			TextView characters;
		}
}