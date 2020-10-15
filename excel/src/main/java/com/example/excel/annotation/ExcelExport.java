package com.example.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导出自定义注解
 *
 * @author guochao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelExport {

    /**
     * 排序字段
     *
     * @return
     */
    int order() default 0;

    /**
     * 导出列中文名称
     *
     * @return
     */
    String zwName() default "";

    /**
     * 实体类字段名称
     *
     * @return
     */
    String baseName() default "";
}
