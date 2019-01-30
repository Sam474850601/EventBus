package com.to8to.myapplication;

import org.greenrobot.eventbus.Event;

/**
 * Created by same.li on 2018/7/30.
 */

public class BaseEvent  extends Event {
    protected String message;

    public BaseEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseEvent:"+message;
    }





}
