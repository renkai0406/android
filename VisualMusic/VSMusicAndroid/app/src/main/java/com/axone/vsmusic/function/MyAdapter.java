package com.axone.vsmusic.function;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by qinxin on 2017/8/20.
 */
public class MyAdapter  extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles_main = {"填词成曲", "由曲成谱", "依景配曲"};
    private String[] titles_other = {"我的喜欢", "我的好友", "附近音乐"};
    private int fragId = 0;

    public MyAdapter(FragmentManager fm, List<Fragment> fragments, int fragId) {
        super(fm);
        this.fragments = fragments;
        this.fragId = fragId;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragId == 0 ? titles_main[position] : titles_other[position];
    }
}

