package com.axone.vsmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.OpernChooseActivity;
import com.axone.vsmusic.opern.ClefSymbol;
import com.axone.vsmusic.opern.MidiPlayer;
import com.axone.vsmusic.opern.TimeSigSymbol;

/**
 * Created by 秋水 on 2017/9/15.
 */

public class OpernFragment extends Fragment{

    private ImageButton bt_choose;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.viewpager_opern, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bt_choose = (ImageButton) view.findViewById(R.id.om_choose);
        bt_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseSong();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** Start the ChooseSongActivity when the "Choose Song" button is clicked */
    private void chooseSong() {
        Intent intent = new Intent(getActivity(), OpernChooseActivity.class);
        startActivity(intent);
    }

    /** Load all the resource images */
    private void loadImages() {
        ClefSymbol.LoadImages(getActivity());
        TimeSigSymbol.LoadImages(getActivity());
        MidiPlayer.LoadImages(getActivity());
    }
}
