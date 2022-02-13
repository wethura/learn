import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wethura
 * @date 2020/11/22 下午6:12
 */
public class TryWithResource {

    public void before() {
        try (InputStream is = new ByteArrayInputStream("".getBytes())) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void after() {
        InputStream is = new ByteArrayInputStream("".getBytes());
        try (is) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
