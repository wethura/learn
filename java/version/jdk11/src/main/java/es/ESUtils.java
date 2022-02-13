package es;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * @author wethura
 * @date 2021/5/24 下午5:04
 */
public class ESUtils {

    protected static final String SEARCH_METHOD_WILDCARD            = "wildcard";
    protected static final String SEARCH_METHOD_RANGE               = "range";
    protected static final String SEARCH_METHOD_TERM                = "term";
    protected static final String SEARCH_METHOD_TERMS               = "terms";
    protected static final String SEARCH_PARAM_VALUE                = "value";
    protected static final String SEARCH_PARAM_BOOST                = "boost";
    protected static final String SEARCH_PARAM_REWRITE              = "rewrite";
    protected static final String SEARCH_PARAM_VALUE_CONSTANT_SCORE = "constant_score";

    public static Map<String, Object> wildcard(String key, String value) {
        return ImmutableMap.of(SEARCH_METHOD_WILDCARD, ImmutableMap.of(key, ImmutableMap.of(
                SEARCH_PARAM_VALUE, "*" + value + "*",
                SEARCH_PARAM_BOOST, 1.0,
                SEARCH_PARAM_REWRITE, SEARCH_PARAM_VALUE_CONSTANT_SCORE)));
    }

    public static Map<String, Object> range(String key, String operator, Long value) {
        return ImmutableMap.of(SEARCH_METHOD_RANGE, ImmutableMap.of(key, ImmutableMap.of(
                operator, value,
                SEARCH_PARAM_BOOST, 2.0)));
    }

    public static Map<String, Object> equal(String key, Object value) {
        return ImmutableMap.of(SEARCH_METHOD_TERM, ImmutableMap.of(key, ImmutableMap.of(
                SEARCH_PARAM_VALUE, value,
                SEARCH_PARAM_BOOST, 1.0)));
    }

    public static <T> Map<String, Object> in(String key, List<T> values) {
        return ImmutableMap.of(SEARCH_METHOD_TERMS, ImmutableMap.of(
                key, values,
                SEARCH_PARAM_BOOST, 1.0));
    }
}
