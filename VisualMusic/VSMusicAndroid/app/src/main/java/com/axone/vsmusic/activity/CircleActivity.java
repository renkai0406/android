package com.axone.vsmusic.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.axone.vsmusic.R;
import com.axone.vsmusic.fragment.CreateSongFragment;
import com.axone.vsmusic.fragment.MatchMusicFragment;
import com.axone.vsmusic.fragment.OpernFragment;
import com.axone.vsmusic.function.MyAdapter;
import com.axone.vsmusic.util.Permission;

import java.util.ArrayList;
import java.util.List;

public class CircleActivity extends AppCompatActivity{

    private TextView tabMusic;
    private TextView tabWork;
    private TextView tabToSong;

    private ViewPager viewPager;
    TabLayout tabLayout;
    private FragmentPagerAdapter adapter;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_parent_main);

        //bindView();
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        fragments = new ArrayList<>();
        fragments.add(new CreateSongFragment());
        fragments.add(new OpernFragment());
        fragments.add(new MatchMusicFragment());

        adapter = new MyAdapter(getSupportFragmentManager(), fragments, 0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Permission permission = new Permission();
        permission.getExternalPermission(CircleActivity.this);

    }
/*
    //UI组件初始化与事件绑定
    private void bindView() {
        tabMusic = (TextView)this.findViewById(R.id.txt_music1);
        tabWork = (TextView)this.findViewById(R.id.txt_work1);
        tabToSong = (TextView)this.findViewById(R.id.txt_tosong1);

        tabMusic.setOnClickListener(this);
        tabWork.setOnClickListener(this);
        tabToSong.setOnClickListener(this);


    }
*/

/*
    //重置所有文本的选中状态
    public void selected(){
        tabMusic.setSelected(false);
        tabWork.setSelected(false);
        tabToSong.setSelected(false);
    }*/
/*
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txt_music1:
                selected();
                tabMusic.setSelected(true);
                break;
            case R.id.txt_work1:
                selected();
                tabWork.setSelected(true);
                break;
            case R.id.txt_tosong1:
                selected();
                tabToSong.setSelected(true);
                break;
        }


    }*/

}
