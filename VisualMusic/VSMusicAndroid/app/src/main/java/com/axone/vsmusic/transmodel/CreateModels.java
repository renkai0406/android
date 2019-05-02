package com.axone.vsmusic.transmodel;

import java.util.LinkedList;

/**
 * Created by 秋水 on 2017/9/2.
 */

public class CreateModels {
    private LinkedList<CreateModel> creates = new LinkedList<>();

    public int getSize() {
        return creates.size();
    }

    public void add(CreateModel cm) {
        creates.add(cm);
    }
}
