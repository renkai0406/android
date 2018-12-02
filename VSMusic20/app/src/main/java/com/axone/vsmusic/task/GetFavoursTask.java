package com.axone.vsmusic.task;

import android.os.AsyncTask;
import android.view.View;

import com.axone.vsmusic.fragment.FavourFragment;
import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.transmodel.SongModels;

/**
 * Created by 秋水 on 2017/10/29.
 */

public class GetFavoursTask extends AsyncTask<Void, Integer, SongModels>{

    private FavourFragment favourFragment;
    private View view;

    public GetFavoursTask(FavourFragment favourFragment, View view){
        this.favourFragment = favourFragment;
        this.view = view;
    }

    @Override
    protected void onPostExecute(SongModels songModels) {
        super.onPostExecute(songModels);
        favourFragment.setFavours(songModels, view);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected SongModels doInBackground(Void... voids) {
        MyClientSocket myClientSocket = MyClientSocket.createClient();
        SongModels songs = myClientSocket.getSongs();
        return songs;
    }
}
