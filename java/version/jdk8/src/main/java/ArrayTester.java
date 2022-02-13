import java.util.Arrays;

/**
 * @author wethura
 * @date 2020/11/22 下午5:21
 */
public class ArrayTester {
    public static void main(String[] args) {
        Arrays.parallelSort(Arrays.asList("json", "sola", "wethura").toArray(new String[0]));
    }
}
