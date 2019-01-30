package org.greenrobot.eventbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by same.li on 2018/7/30.
 * 事件源过滤注解.暂时不使用，待定开放
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface EventFilter {
    /**
     * 额外约束条件。如果有值，那么备注解方法只会收到带有这个action值的事件
     * @return
     */
    String action();
}
