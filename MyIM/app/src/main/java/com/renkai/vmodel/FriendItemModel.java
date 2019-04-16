package com.renkai.vmodel;

public class FriendItemModel implements IViewModel {
    private int head;
    private String name;

    public FriendItemModel(int head, String name) {
        this.head = head;
        this.name = name;
    }

    public int getHead() {
        return head;
    }

    public String getName() {
        return name;
    }
}
