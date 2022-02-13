import java.lang.management.*;
import java.util.List;

/**
 * @author wethura
 * @date 2021/5/9 下午8:50
 */
public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().totalMemory();
        Runtime.getRuntime().maxMemory();

        Runtime.getRuntime().availableProcessors();
        Runtime.getRuntime().gc();

        MemoryMXBean mxxb = ManagementFactory.getMemoryMXBean();

        // 堆内存相关信息
        MemoryUsage heap   = mxxb.getHeapMemoryUsage();

        heap.getCommitted();
        heap.getInit();
        heap.getMax();
        heap.getUsed();

        // 非堆内存相关信息
        MemoryUsage noHeap = mxxb.getNonHeapMemoryUsage();

        ThreadMXBean txxb = ManagementFactory.getThreadMXBean();
        // 进程数
        int          threadCount = txxb.getThreadCount();

        List<GarbageCollectorMXBean> gcmxbs = ManagementFactory.getGarbageCollectorMXBeans();
        int                          size   = gcmxbs.size();
        gcmxbs.get(0).getCollectionTime();
        gcmxbs.get(0).getCollectionCount();
        gcmxbs.get(0).getName();

    }
}
