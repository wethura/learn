import com.google.common.base.Strings;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wethura
 * @date 2021/3/10 下午11:49
 */
public class PathVariableUtils {
    private static       Map<DateTimeFormat.ISO, String> isoPattern               = new HashMap<>();
    private static final String                          DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    static {
        isoPattern.put(DateTimeFormat.ISO.DATE, "yyyy-MM-dd");
        isoPattern.put(DateTimeFormat.ISO.DATE_TIME, DEFAULT_DATETIME_PATTERN);
        isoPattern.put(DateTimeFormat.ISO.TIME, "HH:mm:ss");
        isoPattern.put(DateTimeFormat.ISO.NONE, "");
    }

    public static String toPathVariable(Object params) {
        StringBuffer pathVariable = new StringBuffer();

        Class<?> clazz = params.getClass();

        for (Method method : clazz.getMethods()) {
            method.setAccessible(true);

            if (method.getName().startsWith("get") && !Objects.equals(method.getName(), "getClass")) {
                Object defaultValue = null;
                try {
                    defaultValue = method.invoke(params);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    continue;
                }
                if (Objects.isNull(defaultValue)) {
                    continue;
                }

                Class<?> type  = method.getReturnType();
                String   key   = getMethodPropertiesName(method.getName());
                String   value = null;

                if (TemporalAccessor.class.isAssignableFrom(type)) {
                    String pattern = "";
                    try {
                        Field field = clazz.getField(key);
                        field.setAccessible(true);

                        boolean methodFormatter = method.isAnnotationPresent(DateTimeFormat.class);

                        if (field.isAnnotationPresent(DateTimeFormat.class) || methodFormatter) {
                            DateTimeFormat annotation = methodFormatter ? method.getAnnotation(DateTimeFormat.class) : field.getAnnotation(DateTimeFormat.class);

                            if (!Strings.isNullOrEmpty(annotation.pattern())) {
                                pattern = annotation.pattern();
                            } else if (annotation.iso() != null) {
                                pattern = isoPattern.get(annotation.iso());
                            }
                        }
                    } catch (NoSuchFieldException e) {
                    }

                    value = DateTimeFormatter.ofPattern(pattern).format((TemporalAccessor) defaultValue);
                } else {
                    value = String.valueOf(defaultValue);
                }

                pathVariable.append("&" + key + "=" + value);
            }
        }

        return pathVariable.toString();
    }

    protected static String getMethodPropertiesName(String methodName) {
        StringBuffer buffer = new StringBuffer(methodName);
        return buffer.substring(3, 4).toLowerCase() + buffer.substring(4);
    }

    @Test
    public void testConvertToPathVariable() {
        PathVariableParams params = new PathVariableParams(true, "hello word", 1024,
                LocalDateTime.now(), LocalDate.now(), ZonedDateTime.now());
        System.out.println(PathVariableUtils.toPathVariable(params));
    }

    @Test
    public void testNull() {
        System.out.println(PathVariableUtils.toPathVariable(new PathVariableParams()));
    }

    public static class PathVariableParams {

        public Boolean is;
        public String  string;
        public Integer integer;

        //        public int anInt;

        @DateTimeFormat(pattern = DEFAULT_DATETIME_PATTERN)
        public LocalDateTime localDateTime;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        public LocalDate localDate;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        public ZonedDateTime zonedDateTime;

        public PathVariableParams() {
        }

        public PathVariableParams(Boolean is, String string, Integer integer, LocalDateTime localDateTime, LocalDate localDate, ZonedDateTime zonedDateTime) {
            this.is = is;
            this.string = string;
            this.integer = integer;
            this.localDateTime = localDateTime;
            this.localDate = localDate;
            this.zonedDateTime = zonedDateTime;
        }

        public Boolean getIs() {
            return is;
        }

        public void setIs(Boolean is) {
            this.is = is;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public ZonedDateTime getZonedDateTime() {
            return zonedDateTime;
        }

        public void setZonedDateTime(ZonedDateTime zonedDateTime) {
            this.zonedDateTime = zonedDateTime;
        }

        public int getAnInt() {
            return 0;
        }

        public LocalDateTime getTestLocal() {
            return LocalDateTime.now();
        }

        public void setAnInt(int anInt) {
        }
    }
}
