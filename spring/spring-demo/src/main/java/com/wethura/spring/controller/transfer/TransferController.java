package com.wethura.spring.controller.transfer;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sola
 **/
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Value("${call.endpoint}")
    private String next;

    @GetMapping
    public String transfer() throws IOException {

        final OkHttpClient client = new Builder().build();

        final Request request = new Request.Builder().get().url(next).build();
        try {
            final Response resp = client.newCall(request).execute();
            return resp.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
