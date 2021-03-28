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
    comments = "Source: curtains.proto")
public final class curtainsGrpc {

  private curtainsGrpc() {}

  public static final String SERVICE_NAME = "curtains";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SB.grpc.CRequest,
      SB.grpc.APICResponse> getCswitchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cswitch",
      requestType = SB.grpc.CRequest.class,
      responseType = SB.grpc.APICResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.CRequest,
      SB.grpc.APICResponse> getCswitchMethod() {
    io.grpc.MethodDescriptor<SB.grpc.CRequest, SB.grpc.APICResponse> getCswitchMethod;
    if ((getCswitchMethod = curtainsGrpc.getCswitchMethod) == null) {
      synchronized (curtainsGrpc.class) {
        if ((getCswitchMethod = curtainsGrpc.getCswitchMethod) == null) {
          curtainsGrpc.getCswitchMethod = getCswitchMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.CRequest, SB.grpc.APICResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "curtains", "cswitch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.CRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APICResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new curtainsMethodDescriptorSupplier("cswitch"))
                  .build();
          }
        }
     }
     return getCswitchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SB.grpc.AllC,
      SB.grpc.APIAllC> getCswitchallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cswitchall",
      requestType = SB.grpc.AllC.class,
      responseType = SB.grpc.APIAllC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<SB.grpc.AllC,
      SB.grpc.APIAllC> getCswitchallMethod() {
    io.grpc.MethodDescriptor<SB.grpc.AllC, SB.grpc.APIAllC> getCswitchallMethod;
    if ((getCswitchallMethod = curtainsGrpc.getCswitchallMethod) == null) {
      synchronized (curtainsGrpc.class) {
        if ((getCswitchallMethod = curtainsGrpc.getCswitchallMethod) == null) {
          curtainsGrpc.getCswitchallMethod = getCswitchallMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.AllC, SB.grpc.APIAllC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "curtains", "cswitchall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.AllC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APIAllC.getDefaultInstance()))
                  .setSchemaDescriptor(new curtainsMethodDescriptorSupplier("cswitchall"))
                  .build();
          }
        }
     }
     return getCswitchallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static curtainsStub newStub(io.grpc.Channel channel) {
    return new curtainsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static curtainsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new curtainsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static curtainsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new curtainsFutureStub(channel);
  }

  /**
   */
  public static abstract class curtainsImplBase implements io.grpc.BindableService {

    /**
     */
    public void cswitch(SB.grpc.CRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APICResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCswitchMethod(), responseObserver);
    }

    /**
     */
    public void cswitchall(SB.grpc.AllC request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAllC> responseObserver) {
      asyncUnimplementedUnaryCall(getCswitchallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCswitchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.CRequest,
                SB.grpc.APICResponse>(
                  this, METHODID_CSWITCH)))
          .addMethod(
            getCswitchallMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                SB.grpc.AllC,
                SB.grpc.APIAllC>(
                  this, METHODID_CSWITCHALL)))
          .build();
    }
  }

  /**
   */
  public static final class curtainsStub extends io.grpc.stub.AbstractStub<curtainsStub> {
    private curtainsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private curtainsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected curtainsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new curtainsStub(channel, callOptions);
    }

    /**
     */
    public void cswitch(SB.grpc.CRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APICResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCswitchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cswitchall(SB.grpc.AllC request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAllC> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCswitchallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class curtainsBlockingStub extends io.grpc.stub.AbstractStub<curtainsBlockingStub> {
    private curtainsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private curtainsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected curtainsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new curtainsBlockingStub(channel, callOptions);
    }

    /**
     */
    public SB.grpc.APICResponse cswitch(SB.grpc.CRequest request) {
      return blockingUnaryCall(
          getChannel(), getCswitchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<SB.grpc.APIAllC> cswitchall(
        SB.grpc.AllC request) {
      return blockingServerStreamingCall(
          getChannel(), getCswitchallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class curtainsFutureStub extends io.grpc.stub.AbstractStub<curtainsFutureStub> {
    private curtainsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private curtainsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected curtainsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new curtainsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.APICResponse> cswitch(
        SB.grpc.CRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCswitchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CSWITCH = 0;
  private static final int METHODID_CSWITCHALL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final curtainsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(curtainsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CSWITCH:
          serviceImpl.cswitch((SB.grpc.CRequest) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APICResponse>) responseObserver);
          break;
        case METHODID_CSWITCHALL:
          serviceImpl.cswitchall((SB.grpc.AllC) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APIAllC>) responseObserver);
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

  private static abstract class curtainsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    curtainsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SB.grpc.Curtains.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("curtains");
    }
  }

  private static final class curtainsFileDescriptorSupplier
      extends curtainsBaseDescriptorSupplier {
    curtainsFileDescriptorSupplier() {}
  }

  private static final class curtainsMethodDescriptorSupplier
      extends curtainsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    curtainsMethodDescriptorSupplier(String methodName) {
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
      synchronized (curtainsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new curtainsFileDescriptorSupplier())
              .addMethod(getCswitchMethod())
              .addMethod(getCswitchallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
