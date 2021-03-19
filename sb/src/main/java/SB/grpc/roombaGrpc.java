package SB.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: roomba.proto")
public final class roombaGrpc {

  private roombaGrpc() {}

  public static final String SERVICE_NAME = "roomba";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SB.grpc.Roomba.RoombaRequest,
      SB.grpc.Roomba.APIRoomResponse> getRoombaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "roomba",
      requestType = SB.grpc.Roomba.RoombaRequest.class,
      responseType = SB.grpc.Roomba.APIRoomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.Roomba.RoombaRequest,
      SB.grpc.Roomba.APIRoomResponse> getRoombaMethod() {
    io.grpc.MethodDescriptor<SB.grpc.Roomba.RoombaRequest, SB.grpc.Roomba.APIRoomResponse> getRoombaMethod;
    if ((getRoombaMethod = roombaGrpc.getRoombaMethod) == null) {
      synchronized (roombaGrpc.class) {
        if ((getRoombaMethod = roombaGrpc.getRoombaMethod) == null) {
          roombaGrpc.getRoombaMethod = getRoombaMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.Roomba.RoombaRequest, SB.grpc.Roomba.APIRoomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "roomba", "roomba"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.Roomba.RoombaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.Roomba.APIRoomResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new roombaMethodDescriptorSupplier("roomba"))
                  .build();
          }
        }
     }
     return getRoombaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SB.grpc.Roomba.REmpty,
      SB.grpc.Roomba.APIRoomResponse> getRoombaoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "roombaout",
      requestType = SB.grpc.Roomba.REmpty.class,
      responseType = SB.grpc.Roomba.APIRoomResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.Roomba.REmpty,
      SB.grpc.Roomba.APIRoomResponse> getRoombaoutMethod() {
    io.grpc.MethodDescriptor<SB.grpc.Roomba.REmpty, SB.grpc.Roomba.APIRoomResponse> getRoombaoutMethod;
    if ((getRoombaoutMethod = roombaGrpc.getRoombaoutMethod) == null) {
      synchronized (roombaGrpc.class) {
        if ((getRoombaoutMethod = roombaGrpc.getRoombaoutMethod) == null) {
          roombaGrpc.getRoombaoutMethod = getRoombaoutMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.Roomba.REmpty, SB.grpc.Roomba.APIRoomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "roomba", "roombaout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.Roomba.REmpty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.Roomba.APIRoomResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new roombaMethodDescriptorSupplier("roombaout"))
                  .build();
          }
        }
     }
     return getRoombaoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static roombaStub newStub(io.grpc.Channel channel) {
    return new roombaStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static roombaBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new roombaBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static roombaFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new roombaFutureStub(channel);
  }

  /**
   */
  public static abstract class roombaImplBase implements io.grpc.BindableService {

    /**
     */
    public void roomba(SB.grpc.Roomba.RoombaRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRoombaMethod(), responseObserver);
    }

    /**
     */
    public void roombaout(SB.grpc.Roomba.REmpty request,
        io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRoombaoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRoombaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.Roomba.RoombaRequest,
                SB.grpc.Roomba.APIRoomResponse>(
                  this, METHODID_ROOMBA)))
          .addMethod(
            getRoombaoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.Roomba.REmpty,
                SB.grpc.Roomba.APIRoomResponse>(
                  this, METHODID_ROOMBAOUT)))
          .build();
    }
  }

  /**
   */
  public static final class roombaStub extends io.grpc.stub.AbstractStub<roombaStub> {
    private roombaStub(io.grpc.Channel channel) {
      super(channel);
    }

    private roombaStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected roombaStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new roombaStub(channel, callOptions);
    }

    /**
     */
    public void roomba(SB.grpc.Roomba.RoombaRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRoombaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void roombaout(SB.grpc.Roomba.REmpty request,
        io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRoombaoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class roombaBlockingStub extends io.grpc.stub.AbstractStub<roombaBlockingStub> {
    private roombaBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private roombaBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected roombaBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new roombaBlockingStub(channel, callOptions);
    }

    /**
     */
    public SB.grpc.Roomba.APIRoomResponse roomba(SB.grpc.Roomba.RoombaRequest request) {
      return blockingUnaryCall(
          getChannel(), getRoombaMethod(), getCallOptions(), request);
    }

    /**
     */
    public SB.grpc.Roomba.APIRoomResponse roombaout(SB.grpc.Roomba.REmpty request) {
      return blockingUnaryCall(
          getChannel(), getRoombaoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class roombaFutureStub extends io.grpc.stub.AbstractStub<roombaFutureStub> {
    private roombaFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private roombaFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected roombaFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new roombaFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.Roomba.APIRoomResponse> roomba(
        SB.grpc.Roomba.RoombaRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRoombaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.Roomba.APIRoomResponse> roombaout(
        SB.grpc.Roomba.REmpty request) {
      return futureUnaryCall(
          getChannel().newCall(getRoombaoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ROOMBA = 0;
  private static final int METHODID_ROOMBAOUT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final roombaImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(roombaImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ROOMBA:
          serviceImpl.roomba((SB.grpc.Roomba.RoombaRequest) request,
              (io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse>) responseObserver);
          break;
        case METHODID_ROOMBAOUT:
          serviceImpl.roombaout((SB.grpc.Roomba.REmpty) request,
              (io.grpc.stub.StreamObserver<SB.grpc.Roomba.APIRoomResponse>) responseObserver);
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

  private static abstract class roombaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    roombaBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SB.grpc.Roomba.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("roomba");
    }
  }

  private static final class roombaFileDescriptorSupplier
      extends roombaBaseDescriptorSupplier {
    roombaFileDescriptorSupplier() {}
  }

  private static final class roombaMethodDescriptorSupplier
      extends roombaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    roombaMethodDescriptorSupplier(String methodName) {
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
      synchronized (roombaGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new roombaFileDescriptorSupplier())
              .addMethod(getRoombaMethod())
              .addMethod(getRoombaoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
