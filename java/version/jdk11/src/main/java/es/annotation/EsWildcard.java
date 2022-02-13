package es.annotation;

/**
 * must be the keyword field of ElasticSearch, Or else will get some error.
 * @author wethura
 * @date 2021/5/25 上午1:19
 */
public @interface EsWildcard {
    String field();

    String value();
}
