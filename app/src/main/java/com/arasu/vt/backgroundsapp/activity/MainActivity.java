package com.arasu.vt.backgroundsapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.arasu.vt.backgroundsapp.R;
import com.arasu.vt.backgroundsapp.adapters.GetPhotosAdapter;
import com.arasu.vt.backgroundsapp.base.PhotosPresenter;
import com.arasu.vt.backgroundsapp.fragment.HomeFragment;
import com.arasu.vt.backgroundsapp.interfaces.POJOInterface;
import com.arasu.vt.backgroundsapp.interfaces.PhotoViewInterface;
import com.arasu.vt.backgroundsapp.pojo.PhotoResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

  //  private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        HomeFragment fragment=new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();
    }


}
