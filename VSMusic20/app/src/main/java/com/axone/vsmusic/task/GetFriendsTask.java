package com.axone.vsmusic.task;

import android.os.AsyncTask;

import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.transmodel.FriendModels;

/**
 * Created by 秋水 on 2017/10/31.
 */

public class GetFriendsTask extends AsyncTask<Void, Integer, FriendModels> {

    public GetFriendsTask() {
        super();
    }

    @Override
    protected void onPostExecute(FriendModels friendModels) {
        super.onPostExecute(friendModels);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected FriendModels doInBackground(Void... voids) {
        MyClientSocket myClientSocket = MyClientSocket.createClient();
        FriendModels friendModels = myClientSocket.getFriends();
        return friendModels;
    }
}
