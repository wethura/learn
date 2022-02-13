package query.annotation;

/**
 * @author wethura
 * @date 2021/3/12 上午12:52
 */
public @interface Filter {

    /**
     * the compare method.
     */
    Method method() default Method.EQUAL;

    /**
     * the properties to compare.
     */
    String properties();

    public static enum Method {
        EQUAL,
        CONTAIN,
        MORE_THAN,
        LESS_THAN
    }
}
