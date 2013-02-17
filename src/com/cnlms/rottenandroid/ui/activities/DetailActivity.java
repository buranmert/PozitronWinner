package com.cnlms.rottenandroid.ui.activities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.cnlms.rottenandroid.R;
import com.cnlms.rottenandroid.ui.adapters.CastAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends Activity{
	
	private String imdb;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent data= this.getIntent();
        String title = data.getStringExtra("title");
        String synopsis = data.getStringExtra("synopsis");
        int id = data.getIntExtra("id", -1);
        int rating = data.getIntExtra("rating", -1);
        String thumbnailURL = data.getStringExtra("thumbnail");
        HashMap<String,String> cast_serial = (HashMap<String,String>) data.getSerializableExtra("cast");
        //View view = this.getLayoutInflater().inflate(R.layout.detail_activity, null);
        TextView title_view = (TextView) findViewById(R.id.detail_title);
        title_view.setText(title);
        TextView synopsis_view = (TextView) findViewById(R.id.detail_synopsis);
        synopsis_view.setText(synopsis);
        CastAdapter castadapter = new CastAdapter(this, R.layout.cast_item, cast_serial);
        ListView cast_list = (ListView) findViewById(R.id.list_cast);
        imdb = data.getStringExtra("imdb");
        cast_list.setAdapter(castadapter);
        
        Button imdb_but = (Button) findViewById(R.id.button1);
        imdb_but.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String url = "http://www.imdb.com/title/tt" + imdb;
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
        	
        });
	}
}
