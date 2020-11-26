package com.marstalk.pluggableannotationprocessingapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用了该注解的类会在编译期生成对应的xxxBuilder
 * @author shanzhonglaosou
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BuilderProperty {
}
