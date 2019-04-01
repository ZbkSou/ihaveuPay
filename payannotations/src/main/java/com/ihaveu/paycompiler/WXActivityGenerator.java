package com.ihaveu.paycompiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: bkzhou
 * Date: 2018/9/29
 * Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface WXActivityGenerator {
    //声明该注解所要生成的包名规则
    String getPackageName();
    //声明该注解所生成java类需要继承哪个父类
    Class<?> getSupperClass();
}
