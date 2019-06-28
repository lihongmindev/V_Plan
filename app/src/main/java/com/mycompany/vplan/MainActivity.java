package com.mycompany.vplan;

import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mycompany.vplan.fragment.DeskFragment;
import com.mycompany.vplan.fragment.ShopFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;

    private TabLayout.Tab shop;
    private TabLayout.Tab desk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
    }

    private void initViews() {

        mTablayout= findViewById(R.id.tabLayout);
        mViewPager= findViewById(R.id.viewPager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"商店", "课桌"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new ShopFragment();
                }
                return new DeskFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];           //显示底部的文字
            }

        });

        mTablayout.setupWithViewPager(mViewPager);

        shop = mTablayout.getTabAt(0);
        desk = mTablayout.getTabAt(1);

        shop.setIcon(getResources().getDrawable(R.drawable.shop_up));
        desk.setIcon(getResources().getDrawable(R.drawable.desk_down));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(mTablayout.getTabAt(1)).select();
        }

        /*//侧滑菜单
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //获得NavigationView的list view,从而绑定里面的控件
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });*/

    }
    private void initEvents() {

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == mTablayout.getTabAt(0)) {
                    shop.setIcon(getResources().getDrawable(R.drawable.shop_down));
                    mViewPager.setCurrentItem(0);
                } else if (tab == mTablayout.getTabAt(1)) {
                    desk.setIcon(getResources().getDrawable(R.drawable.desk_down));
                    mViewPager.setCurrentItem(1);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == mTablayout.getTabAt(0)) {
                    shop.setIcon(getResources().getDrawable(R.drawable.shop_up));
                } else if (tab == mTablayout.getTabAt(1)) {
                    desk.setIcon(getResources().getDrawable(R.drawable.desk_up));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
