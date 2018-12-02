package com.axone.vsmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.axone.vsmusic.R;
import com.axone.vsmusic.listener.FavourOnClickListener;
import com.axone.vsmusic.task.GetFavoursTask;
import com.axone.vsmusic.transmodel.SongModel;
import com.axone.vsmusic.transmodel.SongModels;
import com.axone.vsmusic.view.FavourView;

/**
 * Created by 秋水 on 2017/9/12.
 */

public class FavourFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_favours, container, false);

        GetFavoursTask getFavoursTask = new GetFavoursTask(this, view);
        getFavoursTask.execute();

        /*
        LinearLayout vp_favours = (LinearLayout) view.findViewById(R.id.vp_favours);
        FavourView favourView1 = new FavourView(getContext(), null);
        favourView1.setSongName("别说");
        favourView1.setSongCreator("李玖哲");
        favourView1.setIsFavour(true);
        vp_favours.addView(favourView1);

        FavourView favourView2 = new FavourView(getContext(), null);
        favourView2.setSongName("湖心");
        favourView2.setSongCreator("张晓旭");
        favourView2.setIsFavour(true);
        vp_favours.addView(favourView2);

        FavourView favourView3 = new FavourView(getContext(), null);
        favourView3.setSongName("梦在燃烧");
        favourView3.setSongCreator("汤子星");
        favourView3.setIsFavour(true);
        vp_favours.addView(favourView3);*/

        return view;
    }

    public void setFavours(SongModels songModels, View view){
        LinearLayout vp_favours = (LinearLayout) view.findViewById(R.id.vp_favours);
        for (int i = 0; i < songModels.getSize(); i++){
            SongModel songModel = songModels.getSong(i);
            FavourView favourView = new FavourView(getContext(), null);
            favourView.setSongName(songModel.getSongname());
            favourView.setSongCreator(songModel.getSinger());
            favourView.setIsFavour(true);
            favourView.setFavourClickListener(new FavourOnClickListener(getActivity(), songModel.getSongname(), songModel.getSongLocation()));
            vp_favours.addView(favourView);
        }
    }
}
