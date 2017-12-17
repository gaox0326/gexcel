package com.github.gaoxue.gexcel.template;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author gaoxue
 */
@Documented
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Column {

    String name() default "";

}
