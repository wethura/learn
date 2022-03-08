package com.wethura.learn.grpc.server;

import com.wethura.learn.grpc.core.auto.HelloGrpc;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloRequest;
import com.wethura.learn.grpc.core.auto.HelloMessage.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GrpcServer {

    private Server server;

    public static void main(String[] args) throws Exception {
        GrpcServer demo = new GrpcServer();

        demo.start();

        demo.blockUnitShutdown();
    }

    private void start() throws Exception {
        int port = 50051;

        server = ServerBuilder.forPort(port)
                .addService(new HelloImpl())
                .build().start();
    }

    private void blockUnitShutdown() throws Exception {
        if (Objects.nonNull(server)) {
            server.awaitTermination();
        }
    }

    static class HelloImpl extends HelloGrpc.HelloImplBase {

        AtomicInteger counter = new AtomicInteger(0);

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            HelloResponse reply = HelloResponse.newBuilder()
                    .setMessage("服务端计数器: " + counter.incrementAndGet() + " Recv: " + request.getName())
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
