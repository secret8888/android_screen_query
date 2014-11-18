package com.emil.clickable_demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

/**
 * 
 * 标识绑定的View
 * 
 * @author jinqiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ViewId {

    int value() default View.NO_ID;
}
