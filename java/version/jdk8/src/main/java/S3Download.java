import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 * @author sola
 */


/**
 * 对UFS访问的API封装
 *
 * @author
 *
 */
public class S3Download {

    public static void main(String[] args) throws Exception {
        getObject();
    }

    /**
     * 获取指定文件
     */
    public static void getObject() throws Exception {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        HttpsURLConnection conn = null;
        try {
            final URL url = new URL("https://s3.yuexiu.com/hqsiku-ufs-qas/6889864234743775232?response-content-disposition=attachment%3Bfilename%2A%3DUTF-8%27%278f836d3dc7ce4ced92ae95b7640ad4ea.jpg&response-content-type=image%2Fjpeg&AWSAccessKeyId=ZLTFZP3ODS7N1IJTQ527&Expires=1643573717&Signature=ERXbX4Pivwk70NqYn5HLk5epPqg%3D");
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(sslSocketFactory());
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(30000);
            conn.setRequestMethod("GET");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            inputStream = conn.getInputStream();
            if (inputStream == null || inputStream.available() < 1) {
                throw new RuntimeException("从UFS获取文件流inputStream为空");
            }
            outputStream = new ByteArrayOutputStream();
            final byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            if (outputStream == null || outputStream.size() < 1) {
                throw new RuntimeException("从UFS获取文件流转换outputStream为空");
            }
//            final Blob data = getObjectBlob(outputStream.toByteArray());
            System.out.println("Size -> " + outputStream.size());
        } catch (Exception e) {
            throw e;
        } finally {
            //关闭输入流
            closeStream(inputStream);
            closeStream(outputStream);
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    protected static void closeStream(InputStream i) {
        try {
            if (null != i) {
                i.close();
            }
        } catch (IOException ignored) {
        }
    }
    protected static void closeStream(OutputStream i) {
        try {
            if (null != i) {
                i.close();
            }
        } catch (IOException ignored) {
        }
    }


    /**
     * 重写X509TrustManager
     * @return X509TrustManager.
     **/
    private static X509TrustManager x509TrustManager() {
        return new X509TrustManager() {

            public void checkClientTrusted(final X509Certificate[] x509Certificates, final String s){
            }

            public void checkServerTrusted(final X509Certificate[] x509Certificates, final String s){
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    /**
     * sslSocketFactory
     * @return SSLSocketFactory.
     **/
    private static SSLSocketFactory sslSocketFactory() {
        try {
            //信任任何链接
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }



    protected static boolean isEmpty(String str) {
        return !(null == str && str.length() == 0);
    }

    /**
     * 信任安全https
     */
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {

        /**
         * 信任安全https
         * @param hostname sso 地址
         * @param session 应用id
         * @return boolean
         */
        public boolean verify(final String hostname, final SSLSession session) {
            return true;
        }
    }
}
