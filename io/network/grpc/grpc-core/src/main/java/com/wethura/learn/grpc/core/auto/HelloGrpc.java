package com.wethura.learn.grpc.core.auto;

import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;

/**
 *
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.31.1)",
        comments = "Source: hello.proto")
public final class HelloGrpc {

    private HelloGrpc() {
    }

    public static final String SERVICE_NAME = "hello.Hello";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<HelloMessage.HelloRequest,
            HelloMessage.HelloResponse> getSayHelloMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "sayHello",
            requestType = HelloMessage.HelloRequest.class,
            responseType = HelloMessage.HelloResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<HelloMessage.HelloRequest,
            HelloMessage.HelloResponse> getSayHelloMethod() {
        io.grpc.MethodDescriptor<HelloMessage.HelloRequest, HelloMessage.HelloResponse> getSayHelloMethod;
        if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
            synchronized (HelloGrpc.class) {
                if ((getSayHelloMethod = HelloGrpc.getSayHelloMethod) == null) {
                    HelloGrpc.getSayHelloMethod = getSayHelloMethod =
                            io.grpc.MethodDescriptor.<HelloMessage.HelloRequest, HelloMessage.HelloResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(
                                            MethodDescriptor.generateFullMethodName(SERVICE_NAME, "sayHello"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            HelloMessage.HelloRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            HelloMessage.HelloResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new HelloMethodDescriptorSupplier("sayHello"))
                                    .build();
                }
            }
        }
        return getSayHelloMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static HelloStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<HelloStub>() {
                    @java.lang.Override
                    public HelloStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new HelloStub(channel, callOptions);
                    }
                };
        return HelloStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static HelloBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloBlockingStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<HelloBlockingStub>() {
                    @java.lang.Override
                    public HelloBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new HelloBlockingStub(channel, callOptions);
                    }
                };
        return HelloBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static HelloFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HelloFutureStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<HelloFutureStub>() {
                    @java.lang.Override
                    public HelloFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new HelloFutureStub(channel, callOptions);
                    }
                };
        return HelloFutureStub.newStub(factory, channel);
    }

    /**
     *
     */
    public static abstract class HelloImplBase implements io.grpc.BindableService {

        /**
         *
         */
        public void sayHello(HelloMessage.HelloRequest request,
                io.grpc.stub.StreamObserver<HelloMessage.HelloResponse> responseObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getSayHelloMethod(),
                            ServerCalls.asyncUnaryCall(
                                    new MethodHandlers<
                                            HelloMessage.HelloRequest,
                                            HelloMessage.HelloResponse>(
                                            this, METHODID_SAY_HELLO)))
                    .build();
        }
    }

    /**
     *
     */
    public static final class HelloStub extends io.grpc.stub.AbstractAsyncStub<HelloStub> {

        private HelloStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HelloStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloStub(channel, callOptions);
        }

        /**
         *
         */
        public void sayHello(HelloMessage.HelloRequest request,
                io.grpc.stub.StreamObserver<HelloMessage.HelloResponse> responseObserver) {
            ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     *
     */
    public static final class HelloBlockingStub extends io.grpc.stub.AbstractBlockingStub<HelloBlockingStub> {

        private HelloBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HelloBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public HelloMessage.HelloResponse sayHello(HelloMessage.HelloRequest request) {
            return ClientCalls.blockingUnaryCall(
                    getChannel(), getSayHelloMethod(), getCallOptions(), request);
        }
    }

    /**
     *
     */
    public static final class HelloFutureStub extends io.grpc.stub.AbstractFutureStub<HelloFutureStub> {

        private HelloFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HelloFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HelloFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<HelloMessage.HelloResponse> sayHello(
                HelloMessage.HelloRequest request) {
            return ClientCalls.futureUnaryCall(
                    getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final HelloImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(HelloImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    serviceImpl.sayHello((HelloMessage.HelloRequest) request,
                            (io.grpc.stub.StreamObserver<HelloMessage.HelloResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class HelloBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        HelloBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return HelloMessage.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Hello");
        }
    }

    private static final class HelloFileDescriptorSupplier
            extends HelloBaseDescriptorSupplier {

        HelloFileDescriptorSupplier() {
        }
    }

    private static final class HelloMethodDescriptorSupplier
            extends HelloBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final String methodName;

        HelloMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (HelloGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new HelloFileDescriptorSupplier())
                            .addMethod(getSayHelloMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
