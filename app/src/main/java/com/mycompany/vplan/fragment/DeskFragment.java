package com.mycompany.vplan.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.vplan.R;


public class DeskFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private Button homeButton;
    private Button collectButton;
    private NavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desk, container, false);
        //侧滑菜单
        mDrawerLayout = ((AppCompatActivity) getActivity()).findViewById(R.id.drawer_layout);
        homeButton = view.findViewById(R.id.home_button);
        collectButton = view.findViewById(R.id.collect_button);
        //获得NavigationView的list view,从而绑定里面的控件
        navigationView = ((AppCompatActivity) getActivity()).findViewById(R.id.nav_view);

        //获得NavigationView的头部view,从而绑定里面的控件
       /* View view = navigationView.getHeaderView(0);
        TextView textView = view.findViewById(R.id.lixian);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                   //离线下载监听事件
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.
                            WRITE_EXTERNAL_STORAGE}, 1);     //权限请求
                } else {
                    presenterNews.downloadNews(MainActivity.this);
                }
            }
        });*/
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d("Desk","点击home按钮");
            }
        });
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }



    /**
     * Fragment中初始化Toolbar
     * @param toolbar
     * @param isDisplayHomeAsUp 是否显示返回箭头
     */
    public void initToolbar(Toolbar toolbar, boolean isDisplayHomeAsUp) {
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }
        ActionBar actionBar = null;
        if (appCompatActivity != null) {
            actionBar = appCompatActivity.getSupportActionBar();
        }
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }
    /*public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {  //Fragment让Toolbar菜单生效
        Log.d("deskFragment", "onCreateOptionsMenu");
        inflater.inflate(R.menu.desk_toolbar, menu);
         //super.onCreateOptionsMenu(menu,inflater);
    }*/

    /*public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(mAppCompatActivity, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(mAppCompatActivity, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }*/







}
