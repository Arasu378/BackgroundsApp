package com.arasu.vt.backgroundsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.arasu.vt.backgroundsapp.R;
import com.squareup.picasso.Picasso;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class FullScreenActivity extends AppCompatActivity {
    private ImageViewTouch imgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        imgDisplay=(ImageViewTouch)findViewById(R.id.imgDisplay);
        try{
            Bundle bundle=getIntent().getExtras();
            String url=bundle.getString("url");
            Log.d("URl : ",""+url);
            if(url!=null){
                Picasso.with(FullScreenActivity.this).load(url).into(imgDisplay);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
