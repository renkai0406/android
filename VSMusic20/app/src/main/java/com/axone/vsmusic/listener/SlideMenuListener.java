package com.axone.vsmusic.listener;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.SlideActivity;
import com.axone.vsmusic.fragment.parent.MainFragment;
import com.axone.vsmusic.fragment.parent.OtherFragment;

/**
 * Created by 秋水 on 2017/9/11.
 */

public class SlideMenuListener implements NavigationView.OnNavigationItemSelectedListener{

    private SlideActivity activity;

    public SlideMenuListener(SlideActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        //如果点击了当前Tab，直接关闭
        if(id == activity.getCurTab()){
            drawer.closeDrawers();
            return false;
        }
        //否则切换到对应Tab
        switch (id) {
            case R.id.nav_to_songs:
                setTab(0, 0);
                break;
            case R.id.nav_to_opern:
                setTab(1, 0);
                break;
            case R.id.nav_match_song:
                setTab(2, 0);
                break;
            case R.id.nav_my_likes:
                setTab(0, 1);
                break;
            case R.id.nav_my_friends:
                setTab(1, 1);
                break;
            case R.id.nav_arround:
                setTab(2, 1);
                break;
            case R.id.nav_my_info:
                toOtherActivity(0);
                break;
            case R.id.nav_settings:
                toOtherActivity(1);
                break;
            default:
                break;
        }
        activity.setCurTab(id);
        item.setChecked(true);
        drawer.closeDrawers();
        return true;

    }

    private void setTab(int tabId, int fragId){
        Fragment fragment = null;
        if(fragId != activity.getCurFragment()){
            if(fragId == 0)
                fragment = activity.getMainFragment();
            if(fragId == 1)
                fragment = activity.getOtherFragment();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, "Current").commit();
        }
        activity.setCurFragment(fragId);

       fragment = activity.getSupportFragmentManager().findFragmentByTag("Current");
        if(fragment instanceof MainFragment){
            ((MainFragment)fragment).setPagerItem(tabId);
        }else if(fragment instanceof OtherFragment){
            ((OtherFragment)fragment).setPagerItem(tabId);
        }
    }

    private void toOtherActivity(int id){

    }
}
