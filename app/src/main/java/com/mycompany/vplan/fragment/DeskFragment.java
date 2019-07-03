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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.vplan.R;
import com.mycompany.vplan.adapter.DeskRecyclerAdapter;
import com.mycompany.vplan.bean.CardList;
import com.mycompany.vplan.presenter.DeskPresenter;
import com.mycompany.vplan.view.IDeskView;

import java.util.List;


public class DeskFragment extends Fragment implements IDeskView {

    private DrawerLayout mDrawerLayout;
    private Button homeButton;
    private Button collectButton;
    private NavigationView navigationView;
    private DeskRecyclerAdapter deskRecyclerAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private DeskPresenter deskPresenter;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private static final String [] subject ={"全部科目","数学","英语","语文","物理","化学","生物","历史","地理","政治"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desk, container, false);
        //侧滑菜单
        mDrawerLayout = ((AppCompatActivity) getActivity()).findViewById(R.id.drawer_layout);
        homeButton = view.findViewById(R.id.home_button);
        collectButton = view.findViewById(R.id.collect_button);
        //获得NavigationView的list view,从而绑定里面的控件
        navigationView = ((AppCompatActivity) getActivity()).findViewById(R.id.nav_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager((getActivity()),3);
        spinner = view.findViewById(R.id.spinner);


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
        navigationView.setCheckedItem(R.id.desk);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        deskRecyclerAdapter = new DeskRecyclerAdapter();
        recyclerView.setAdapter(deskRecyclerAdapter);

        adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,subject);
        //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到spinner中去
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);//设置默认显示
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String subjectCollect = subject[position];
                deskPresenter.requestDeskCard(subjectCollect);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //显示全科card
        deskPresenter = new DeskPresenter(this);
        deskPresenter.requestDeskCard("全部科目");



    }


    @Override
    public void showDeskCard(List<CardList> cardLists) {
        deskRecyclerAdapter.UpdateDeskRecyclerAdapter(cardLists);
        deskRecyclerAdapter.notifyDataSetChanged();
    }


}
