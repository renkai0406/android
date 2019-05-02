package com.axone.vsmusic.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.SlideActivity;
import com.axone.vsmusic.fragment.CreateSongFragment;
import com.axone.vsmusic.fragment.MatchMusicFragment;
import com.axone.vsmusic.fragment.OpernFragment;
import com.axone.vsmusic.function.MyAdapter;
import com.axone.vsmusic.util.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 秋水 on 2017/10/26.
 */

public class MainFragment extends Fragment{

    private ViewPager viewPager;
    TabLayout tabLayout;
    private FragmentPagerAdapter adapter;

    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_parent_main, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.main_viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.main_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        fragments = new ArrayList<>();
        fragments.add(new CreateSongFragment());
        fragments.add(new OpernFragment());
        fragments.add(new MatchMusicFragment());

        adapter = new MyAdapter(getChildFragmentManager(), fragments, 0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //更改页面后，通知Activity更改菜单选中项
                ((SlideActivity)getActivity()).setMenuItem(position, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Permission permission = new Permission();
        permission.getExternalPermission(getActivity());
        return view;
    }

    public void setPagerItem(int tabId){
        viewPager.setCurrentItem(tabId);
    }
}
