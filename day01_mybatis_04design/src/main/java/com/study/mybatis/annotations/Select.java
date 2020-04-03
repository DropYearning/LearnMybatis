package com.study.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Select注解：查询
 */

@Retention(RetentionPolicy.RUNTIME) // 注解的声明周期
@Target(ElementType.METHOD) // 注解的出现位置

public @interface Select {
    /**
     * 配置SQL语句
     * @return
     */
    String value();
}
