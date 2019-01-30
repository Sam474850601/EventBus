# EventBus
> EventBus是Android的一个中心发布/订阅事件系统.它相当andriod的中广播作用。
>详细说明可参考 [https://github.com/greenrobot/EventBus](https://github.com/greenrobot/EventBus)      
为了更有效的控制参数的规范化，以及接收作用域的控制，做相应了一些源码的修改，所以下面的例子会和官方的可能有些不一样，不过基本一样。  





##  最新地址

>  implementation 'com.to8to.app.component:component-teventbus:1.0.3'



## 调试模式开关

> 这个开关很==重要==，涉及到cpu计算性能损耗，**如果发版请关掉**

```java

EventBus.setDebugable(true);//为true是调试模式。false是关闭调试模式

```

## 注册订阅以及停止订阅

> 我们知道，订阅监听一个android广播内容的时候，需要注册广播,取消订阅需要反注册。而EventBus也不例外，例子如下


```java
    
public class TestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//订阅注册, 另外：EventBus.getDefault()获取EventBus唯一实例
    }
    

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);//反注册
        super.onDestroy();
    }
}


```

##  订阅与发送事件

> 我们已经知道了，如何注册，反注册。那么接下来如何订阅接收到的内容呢？首先我们需要添加一个实体类，这个实体类相当于数据的载体，用来传输交互使用。这个实体需要继承**org.greenrobot.eventbus.Event**（或者实现**org.greenrobot.eventbus.IEvent**）.
如下是IEvent的接口源码。

```java

/**
 * 
 * 数据事件实现类
 */

public interface IEvent {


    /**
     * 设置发送目标
     * @param action
     */
    void setEventBusAction(String action);

    /**
     * 指定事件发送目标，使得有过滤效果。为空则，全部该类的事件全部可以接收
     * @return
     */
    String getEventBusAction();


    /**
     * 事件源打印信息。。
     * @return
     */
    String getEventBusLogger();

    /**
     * 设置事件源打印信息。
     * @return
     */
    void  setEventBusLogger(String logger);


}


/**
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



```

> 首先，我们定义一个事件数据源，带有message数据， 例子:


```java

public class TestEvent  extends Event {
    protected String message;

    public BaseEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TestEvent:"+message;
    }
    

}



```

### 发送事件

> 如何发送TestEvent？如下:

```java

  //发送数据
   EventBus.getDefault().post(new TestEvent("lalal"));


```
> EventBus的post发送数据事件定义方法。

```java

public class EventBus {
    
    public void  post(IEvent event){
               //为了方便阅读，此处省略1万个草泥码........
    }
}
  


```



### 订阅接收事件
  
> 我们已经知道了发送，那么如何接收呢？在需要注册订阅事件的class下，**用Subscribe**注解订阅接受数据， 其中threadMode代表你期望在哪个线程可以接受到内容，threadMode = ThreadMode.MAIN是代表在主线程接收，(threadMode其他使用，具体看官方文档[https://github.com/greenrobot/EventBus](https://github.com/greenrobot/EventBus) ) ，如下：

 ```java
public class TestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//订阅注册, 另外：EventBus.getDefault()获取EventBus唯一实例
    }
    
        
    //订阅接收所有TestEvent对象。（方法名可以改，可订阅多个类型）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(TestEvent event) {
        Log.e("eventbus", "TestActivity : " + event.toString()+"\t:"+event.logger());//打印事件发送事件源代码
    }



    
    

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);//反注册
        super.onDestroy();
    }

    
}

```

##  过滤订阅以及指定发送事件


> 到这里，我们已经知道了如何简单使用了EventBus。以前用过官方EventBus的人都知道，只要我们发送事件，例如TestEvent，那么所有订阅TestEvent的地方全部能收的到，当我们只想指定发送某些TestEvent订阅类的方法，却做不到。所以本组件做了源码修改，对事件添加作用域过滤功能，可以只指定发送到对应订阅类方法，其他地方不会再收到。实现方式：订阅方法，通过注解**Subscribe**中的action控制指定订阅。这代表只接收class类型一样，且中含action一致的Event子类。如下：


```java
         //发送给haha订阅TestEvent的类
          void sendEventWithAction(){
            TestEvent event = new TestEvent();
            event.setEventBusAction("haha");
            EventBus.getDefault().post(child2Event);
          }

```


> 订阅例子

 ```java
 
 
 
 
 
public class TestActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//订阅注册, 另外：EventBus.getDefault()获取EventBus唯一实例
    }
    
        
    //订阅接收所有TestEvent对象。（方法名可以改，可订阅多个类型）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(TestEvent event) {
        Log.e("eventbus", "TestActivity : " + event.toString()+"\t:"+event.logger());//打印事件发送事件源代码
    }



    //订阅只接收TestEvent类型含action为haha的数据
    @Subscribe(threadMode = ThreadMode.MAIN, action = "haha")
    public void onMessageEventMain2(TestEvent event) {
        Log.e("eventbus", "child2.1: " + event.toString()+"\t:"+event.logger());
    }

    
    

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);//反注册
        super.onDestroy();
    }

    
}

```


## 事件命名规范

> T + 名字 + Event组合，如

```java 

public class TMessageEvent{

}


```

## 注意事项

> 到了这里，已经介绍完毕。记得注意一点，**EventBus**一旦**register**那么退出页面的时候，必须要**unregister**取消订阅。避免**内存泄漏**


