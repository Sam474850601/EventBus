package org.greenrobot.eventbus;

/**
 * Created by same.li on 2018/7/30.
 * 数据事件实现类
 */

public interface IEvent {


    /**
     * 设置
     * @param action
     */
    void setEventBusAction(String action);

    /**
     * 事件发送目标
     * @return
     */
    String getEventBusAction();


    /**
     * 事件源打印信息。
     * @return
     */
    String getEventBusLogger();

    /**
     * 事件源打印信息。
     * @return
     */
    void  setEventBusLogger(String logger);


}
