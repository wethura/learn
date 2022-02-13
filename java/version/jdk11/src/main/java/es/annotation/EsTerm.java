package es.annotation;

/**
 * @author wethura
 * @date 2021/5/25 上午1:18
 */
public @interface EsTerm {
    String field();

    String value();

    Class clazz() default String.class;
}
