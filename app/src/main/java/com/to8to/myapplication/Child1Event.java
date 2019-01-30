package com.to8to.myapplication;

/**
 * Created by same.li on 2018/7/30.
 */

public class Child1Event extends BaseEvent {

    public Child1Event(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Child1Event:"+message;
    }
}
