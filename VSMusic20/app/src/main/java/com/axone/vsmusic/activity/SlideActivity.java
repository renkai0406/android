package com.axone.vsmusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.axone.vsmusic.R;
import com.axone.vsmusic.fragment.parent.MainFragment;
import com.axone.vsmusic.fragment.parent.OtherFragment;
import com.axone.vsmusic.listener.SlideMenuListener;


public class SlideActivity extends AppCompatActivity {

    private NavigationView navigationView;

    private MainFragment mainFragment;
    private OtherFragment otherFragment;

    private int curFragment = 0;
    private int curTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        System.out.println("跳转到Slide界面");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new SlideMenuListener(this));

        if(mainFragment == null)
            mainFragment = new MainFragment();
        if(otherFragment == null)
            otherFragment = new OtherFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mainFragment, "Current").commit();
        navigationView.setCheckedItem(R.id.nav_to_songs);
        curFragment = 0;
        curTab = R.id.nav_to_songs;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            toOther(1);
        } else {
            super.onBackPressed();
        }
    }

    public void toOther(int tag){
 /*       if(tag == 0){

        }else {
            Intent intent = new Intent();
            intent.setClass(this, CircleActivity.class);
            startActivity(intent);
        }*/
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public void setMainFragment(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    public OtherFragment getOtherFragment() {
        return otherFragment;
    }

    public void setOtherFragment(OtherFragment otherFragment) {
        this.otherFragment = otherFragment;
    }

    public int getCurFragment() {
        return curFragment;
    }

    public void setCurFragment(int curFragment) {
        this.curFragment = curFragment;
    }

    public int getCurTab() {
        return curTab;
    }

    public void setCurTab(int curTab) {
        this.curTab = curTab;
    }

    public void setMenuItem(int tabId, int fragId){
        @IdRes int id = 0;
        switch (tabId){
            case 0:
                id = fragId == 0? R.id.nav_to_songs : R.id.nav_my_likes;
                break;
            case 1:
                id = fragId == 0? R.id.nav_to_opern : R.id.nav_my_friends;
                break;
            case 2:
                id = fragId == 0? R.id.nav_match_song : R.id.nav_arround;
                break;
            default:
                break;
        }
        if(id!=0){
            navigationView.setCheckedItem(id);

            curTab = id;
        }
    }
}
