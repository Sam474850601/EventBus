package org.greenrobot.eventbus;

/**
 * Created by same.li on 2018/7/30.
 * EventBus事件基类。
 */

public class Event  implements IEvent{

    private String eventBusAction;

    private String eventBusLogger;

    public Event(String action) {
        this.eventBusAction = action;
    }

    public Event(){
        this(null);
    }

    @Override
    public void setEventBusAction(String action) {
        this.eventBusAction = action;
    }


    @Override
    public String getEventBusAction() {
        return eventBusAction;
    }

    @Override
    public String getEventBusLogger() {
        return eventBusLogger;
    }

    @Override
    public void setEventBusLogger(String logger) {
        this.eventBusLogger = logger;
    }






}
