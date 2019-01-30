package com.to8to.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.Event;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by same.li on 2018/7/30.
 */

public class EventBusChild2Activity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbuschild2);
        Log.e("eventbus", "EventBusChild2Activity onCreate ");
        EventBus.getDefault().register(this);

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(Child2Event event) {
        Log.e("eventbus", "EventBusChild2Activity : " + event.toString()+"\t:"+event.getEventBusLogger());
    }


    @Subscribe(threadMode = ThreadMode.MAIN, action = "haha")
    public void onMessageEventMain2(Event event) {
        Log.e("eventbus", "child2.1: " + event.toString()+"\t:"+event.getEventBusLogger());
    }


    @Subscribe(threadMode = ThreadMode.MAIN,action = "hehe")
    public void onMessageEventMain3(Event event) {
        Log.e("eventbus", "child2.2: " + event.toString()+"\t:"+event.getEventBusLogger());
    }



    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onSendEventClick(View view){
         final int id = view.getId();
        Log.e("eventbus", "onSendEventClick ");
        if (id== R.id.btn_1){
            EventBus.getDefault().post(new BaseEvent("lalal"));
        } else if(id== R.id.btn_2){
            Event child2Event = new Event();
            child2Event.setEventBusAction("haha");
            EventBus.getDefault().post(child2Event);
        }
        else  if(id== R.id.btn_3){
            Event child2Event = new Event();
            child2Event.setEventBusAction("hehe");
            EventBus.getDefault().post(child2Event);
        }

    }
}
