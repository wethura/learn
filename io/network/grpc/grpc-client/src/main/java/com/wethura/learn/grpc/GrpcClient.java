package com.wethura.learn.grpc;

import com.wethura.learn.grpc.core.auto.HelloGrpc;
import com.wethura.learn.grpc.core.auto.HelloGrpc.HelloBlockingStub;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloRequest;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcClient {

    public static void main(String[] args) {
        new GrpcClient().remoteCall("Jessy!");
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
