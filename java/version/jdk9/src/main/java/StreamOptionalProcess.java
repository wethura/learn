import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author wethura
 * @date 2020/11/22 下午6:14
 */
public class StreamOptionalProcess {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("json");
        list.add("java");
        list.add("python");

        Stream<String> wethura = list.stream().map(str -> str.equals("json") ? "wethura" : null).filter(str -> !Objects.isNull(str));
        System.out.println(wethura.toArray()[1]);
    }
}
