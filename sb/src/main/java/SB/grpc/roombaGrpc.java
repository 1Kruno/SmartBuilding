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
  private static volatile io.grpc.MethodDescriptor<SB.grpc.CleanSelectedRooms,
      SB.grpc.APICleanSelectedRoomsResponse> getCleanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clean",
      requestType = SB.grpc.CleanSelectedRooms.class,
      responseType = SB.grpc.APICleanSelectedRoomsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SB.grpc.CleanSelectedRooms,
      SB.grpc.APICleanSelectedRoomsResponse> getCleanMethod() {
    io.grpc.MethodDescriptor<SB.grpc.CleanSelectedRooms, SB.grpc.APICleanSelectedRoomsResponse> getCleanMethod;
    if ((getCleanMethod = roombaGrpc.getCleanMethod) == null) {
      synchronized (roombaGrpc.class) {
        if ((getCleanMethod = roombaGrpc.getCleanMethod) == null) {
          roombaGrpc.getCleanMethod = getCleanMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.CleanSelectedRooms, SB.grpc.APICleanSelectedRoomsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "roomba", "clean"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.CleanSelectedRooms.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APICleanSelectedRoomsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new roombaMethodDescriptorSupplier("clean"))
                  .build();
          }
        }
     }
     return getCleanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SB.grpc.CleanAllRooms,
      SB.grpc.APICleanAllRoomsResponse> getCleanAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cleanAll",
      requestType = SB.grpc.CleanAllRooms.class,
      responseType = SB.grpc.APICleanAllRoomsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<SB.grpc.CleanAllRooms,
      SB.grpc.APICleanAllRoomsResponse> getCleanAllMethod() {
    io.grpc.MethodDescriptor<SB.grpc.CleanAllRooms, SB.grpc.APICleanAllRoomsResponse> getCleanAllMethod;
    if ((getCleanAllMethod = roombaGrpc.getCleanAllMethod) == null) {
      synchronized (roombaGrpc.class) {
        if ((getCleanAllMethod = roombaGrpc.getCleanAllMethod) == null) {
          roombaGrpc.getCleanAllMethod = getCleanAllMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.CleanAllRooms, SB.grpc.APICleanAllRoomsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "roomba", "cleanAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.CleanAllRooms.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APICleanAllRoomsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new roombaMethodDescriptorSupplier("cleanAll"))
                  .build();
          }
        }
     }
     return getCleanAllMethod;
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
    public io.grpc.stub.StreamObserver<SB.grpc.CleanSelectedRooms> clean(
        io.grpc.stub.StreamObserver<SB.grpc.APICleanSelectedRoomsResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCleanMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SB.grpc.CleanAllRooms> cleanAll(
        io.grpc.stub.StreamObserver<SB.grpc.APICleanAllRoomsResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCleanAllMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCleanMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SB.grpc.CleanSelectedRooms,
                SB.grpc.APICleanSelectedRoomsResponse>(
                  this, METHODID_CLEAN)))
          .addMethod(
            getCleanAllMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                SB.grpc.CleanAllRooms,
                SB.grpc.APICleanAllRoomsResponse>(
                  this, METHODID_CLEAN_ALL)))
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
    public io.grpc.stub.StreamObserver<SB.grpc.CleanSelectedRooms> clean(
        io.grpc.stub.StreamObserver<SB.grpc.APICleanSelectedRoomsResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCleanMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SB.grpc.CleanAllRooms> cleanAll(
        io.grpc.stub.StreamObserver<SB.grpc.APICleanAllRoomsResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCleanAllMethod(), getCallOptions()), responseObserver);
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
  }

  private static final int METHODID_CLEAN = 0;
  private static final int METHODID_CLEAN_ALL = 1;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLEAN:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clean(
              (io.grpc.stub.StreamObserver<SB.grpc.APICleanSelectedRoomsResponse>) responseObserver);
        case METHODID_CLEAN_ALL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.cleanAll(
              (io.grpc.stub.StreamObserver<SB.grpc.APICleanAllRoomsResponse>) responseObserver);
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
              .addMethod(getCleanMethod())
              .addMethod(getCleanAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
