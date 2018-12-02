package com.axone.vsmusic.transmodel;

import java.util.LinkedList;

/**
 * Created by 秋水 on 2017/9/3.
 */

public class FriendModels {
    private LinkedList<FriendModel> friends = new LinkedList<>();

    public int getSize() {
        return friends.size();
    }

    public void add(FriendModel fm) {
        friends.add(fm);
    }

    public FriendModel getFriend(int index){ return friends.get(index);}
}
