package com.to8to.myapplication;

/**
 * Created by same.li on 2018/7/30.
 */

public class Child2Event extends BaseEvent  {

    public Child2Event(String message) {
        super(message);
    }



    @Override
    public String toString() {
        return "Child2Event:"+message;
    }

}
