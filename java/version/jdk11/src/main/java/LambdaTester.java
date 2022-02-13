import java.lang.management.ManagementFactory;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author wethura
 * @date 2020/11/22 下午10:03
 */
public class LambdaTester {
    public static void main(String[] args) {
        System.out.println(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax());
    }
}
