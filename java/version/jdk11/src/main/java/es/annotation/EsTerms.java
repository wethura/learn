package es.annotation;

/**
 * @author wethura
 * @date 2021/5/25 上午1:19
 */
public @interface EsTerms {
    String field();

    String[] values();

    Class clazz() default String.class;
}
