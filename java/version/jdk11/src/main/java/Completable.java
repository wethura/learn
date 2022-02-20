
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author wethura
 * @date 2021/7/14 上午12:38
 */
public class Completable {
    @Test
    public void testJUCAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Run: Async");
        });

        System.out.println(LocalDateTime.now());
        future.get();
        future.thenApply(unused -> "Json");
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void testJUCThen() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future0 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing future0.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ignored) {
            }
            return "Run: supply async!";
        });
        System.out.println(LocalDateTime.now());
        CompletableFuture<Integer> future1 = future0.thenApply(str -> {
            System.out.println(str);
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException ignored) {
            }
            return 1;
        });
        System.out.println(LocalDateTime.now());

//        System.out.println(future1.complete(2));
        Integer integer = future1.get();

        System.out.println("I get the value: " + integer);

    }

    @Test
    public void test() {

        Function<Integer, Integer> f1 = (i) -> {
            System.out.println(i + " ");
            i = i + 1;
            return i;
        };
        Function<Integer, Integer> f2 = (i) -> {
            System.out.println(i + " ");
            i = i + 1;
            return i;
        };

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(f1);
    }

    @Test
    public void testDateFormat() {
    }
}
