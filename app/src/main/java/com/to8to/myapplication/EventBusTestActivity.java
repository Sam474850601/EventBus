package com.to8to.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by same.li on 2018/7/30.
 */

public class EventBusTestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("eventbus", "EventBusTestActivity onCreate ");
        EventBus.getDefault().register(this);
        startActivity(new Intent(this, EventBusChild1Activity.class));

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(BaseEvent event) {
        Log.e("eventbus", "EventBusTestActivity: " + event.toString()+"\t:"+event.getEventBusLogger());
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
