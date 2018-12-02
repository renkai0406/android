package com.axone.vsmusic.transmodel;

import java.util.LinkedList;

/**
 * Created by 秋水 on 2017/9/1.
 */

public class FavouriteModels{

    private LinkedList<FavouriteModel> favourites = new LinkedList<>();

    public int getSize() {
        return favourites.size();
    }

    public void add(FavouriteModel fm) {
        favourites.add(fm);
    }
}
