package com.wethura.learn.grpc;

import com.wethura.learn.grpc.core.auto.HelloGrpc;
import com.wethura.learn.grpc.core.auto.HelloGrpc.HelloBlockingStub;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloRequest;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 10; i ++) {
            int clientNo = i;
            executorService.submit(() -> {
                GrpcClient client = new GrpcClient();

                while(true) {
                    client.remoteCall(String.format("client-%02d", clientNo));
                }
            });
        }

        TimeUnit.SECONDS.sleep(60);
    }

    public void remoteCall(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName("GRPC_CLIENT")
                .build();
        HelloResponse response;

        try {
            Channel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
                    .usePlaintext().build();
            HelloBlockingStub blockingStub = HelloGrpc.newBlockingStub(channel);

            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException sre) {
            return;
        }

        System.out.println("服务端日志: " + response.getMessage());
    }
}
