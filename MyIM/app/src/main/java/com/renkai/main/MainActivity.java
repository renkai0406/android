package com.renkai.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.renkai.R;
import com.renkai.fragment.FriendFragment;
import com.renkai.fragment.MsgFragment;
import com.renkai.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private List<FragmentData> fragments;

    private FragmentPagerAdapter adapter;

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(viewPager.getCurrentItem() != 0){
                        viewPager.setCurrentItem(0);
                        setTitle(fragments.get(0).title);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(viewPager.getCurrentItem() != 1){
                        viewPager.setCurrentItem(1);
                        setTitle(fragments.get(1).title);
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(viewPager.getCurrentItem() != 2){
                        viewPager.setCurrentItem(2);
                        setTitle(fragments.get(2).title);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.viewPager = findViewById(R.id.main_viewpager);
        this.navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(this.fragments == null){
            this.fragments = new ArrayList<>();
            this.fragments.add(new FragmentData(new MsgFragment(), "消息"));
            this.fragments.add(new FragmentData(new FriendFragment(), "好友"));
            this.fragments.add(new FragmentData(new OtherFragment(), "其他"));
        }

        setTitle(fragments.get(0).title);

        this.adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i).fragment;
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        };

        this.viewPager.setAdapter(this.adapter);

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

     private class FragmentData{
        public Fragment fragment;
        public String title;

        public FragmentData(Fragment fragment, String title){
            this.fragment = fragment;
            this.title = title;
        }
     }

}
