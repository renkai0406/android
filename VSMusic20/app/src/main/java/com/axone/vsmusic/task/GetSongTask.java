package com.axone.vsmusic.task;

import android.os.AsyncTask;

import com.axone.vsmusic.activity.PlayingCreatedActivity;
import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.translation.Type;

/**
 * Created by 秋水 on 2017/10/30.
 */

public class GetSongTask extends AsyncTask<String, Integer, String> implements PlayingTask {

    private PlayingCreatedActivity pcActivity;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pcActivity.initPlay();
        pcActivity.startPlay(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        //向服务器请求文件，路径path
        //向本地写入文件
        MyClientSocket myClientSocket = MyClientSocket.createClient();
        String path = myClientSocket.getSong(strings[0], strings[1]);

        return path;
    }

    @Override
    public void setPCAcitivity(PlayingCreatedActivity activity) {
        pcActivity = activity;
    }
}
