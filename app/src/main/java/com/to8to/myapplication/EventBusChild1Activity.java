package com.to8to.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.Event;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by same.li on 2018/7/30.
 */

public class EventBusChild1Activity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        startActivity(new Intent(this, EventBusChild2Activity.class));
        Log.e("eventbus", "EventBusChild1Activity onCreate ");
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(Event event) {
        Log.e("eventbus", "EventBusChild1Activity : " + event.toString()+"\t:"+event.getEventBusLogger());
    }





    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
