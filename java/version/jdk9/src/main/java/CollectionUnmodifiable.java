import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wethura
 * @date 2020/11/22 下午6:01
 */
public class CollectionUnmodifiable {
    public void before() {
        Set<String> set = new HashSet<>();

        set.add("json");
        set.add("java");
        set.add("python");

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);

//        would not support any opt for array.
//        unmodifiableSet.add("lico");
        System.out.println(unmodifiableSet);
    }

    public void after() {
        Set<String> set = Set.of("json", "java", "python");

        set.add("lico");
    }

    public static void main(String[] args) {
//        new CollectionUnmodifiable().before();
        new CollectionUnmodifiable().after();
    }
}
