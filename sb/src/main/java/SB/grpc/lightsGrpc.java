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
    comments = "Source: lights.proto")
public final class lightsGrpc {

  private lightsGrpc() {}

  public static final String SERVICE_NAME = "lights";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SB.grpc.SwitchRequest,
      SB.grpc.APILightsResponse> getLightswitchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lightswitch",
      requestType = SB.grpc.SwitchRequest.class,
      responseType = SB.grpc.APILightsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.SwitchRequest,
      SB.grpc.APILightsResponse> getLightswitchMethod() {
    io.grpc.MethodDescriptor<SB.grpc.SwitchRequest, SB.grpc.APILightsResponse> getLightswitchMethod;
    if ((getLightswitchMethod = lightsGrpc.getLightswitchMethod) == null) {
      synchronized (lightsGrpc.class) {
        if ((getLightswitchMethod = lightsGrpc.getLightswitchMethod) == null) {
          lightsGrpc.getLightswitchMethod = getLightswitchMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.SwitchRequest, SB.grpc.APILightsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "lights", "lightswitch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.SwitchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APILightsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new lightsMethodDescriptorSupplier("lightswitch"))
                  .build();
          }
        }
     }
     return getLightswitchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SB.grpc.SwitchOffAllLights,
      SB.grpc.APISwitchOffAllLights> getLightswitchallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lightswitchall",
      requestType = SB.grpc.SwitchOffAllLights.class,
      responseType = SB.grpc.APISwitchOffAllLights.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<SB.grpc.SwitchOffAllLights,
      SB.grpc.APISwitchOffAllLights> getLightswitchallMethod() {
    io.grpc.MethodDescriptor<SB.grpc.SwitchOffAllLights, SB.grpc.APISwitchOffAllLights> getLightswitchallMethod;
    if ((getLightswitchallMethod = lightsGrpc.getLightswitchallMethod) == null) {
      synchronized (lightsGrpc.class) {
        if ((getLightswitchallMethod = lightsGrpc.getLightswitchallMethod) == null) {
          lightsGrpc.getLightswitchallMethod = getLightswitchallMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.SwitchOffAllLights, SB.grpc.APISwitchOffAllLights>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "lights", "lightswitchall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.SwitchOffAllLights.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APISwitchOffAllLights.getDefaultInstance()))
                  .setSchemaDescriptor(new lightsMethodDescriptorSupplier("lightswitchall"))
                  .build();
          }
        }
     }
     return getLightswitchallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static lightsStub newStub(io.grpc.Channel channel) {
    return new lightsStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static lightsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new lightsBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static lightsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new lightsFutureStub(channel);
  }

  /**
   */
  public static abstract class lightsImplBase implements io.grpc.BindableService {

    /**
     */
    public void lightswitch(SB.grpc.SwitchRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APILightsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLightswitchMethod(), responseObserver);
    }

    /**
     */
    public void lightswitchall(SB.grpc.SwitchOffAllLights request,
        io.grpc.stub.StreamObserver<SB.grpc.APISwitchOffAllLights> responseObserver) {
      asyncUnimplementedUnaryCall(getLightswitchallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLightswitchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.SwitchRequest,
                SB.grpc.APILightsResponse>(
                  this, METHODID_LIGHTSWITCH)))
          .addMethod(
            getLightswitchallMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                SB.grpc.SwitchOffAllLights,
                SB.grpc.APISwitchOffAllLights>(
                  this, METHODID_LIGHTSWITCHALL)))
          .build();
    }
  }

  /**
   */
  public static final class lightsStub extends io.grpc.stub.AbstractStub<lightsStub> {
    private lightsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightsStub(channel, callOptions);
    }

    /**
     */
    public void lightswitch(SB.grpc.SwitchRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APILightsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLightswitchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lightswitchall(SB.grpc.SwitchOffAllLights request,
        io.grpc.stub.StreamObserver<SB.grpc.APISwitchOffAllLights> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getLightswitchallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class lightsBlockingStub extends io.grpc.stub.AbstractStub<lightsBlockingStub> {
    private lightsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightsBlockingStub(channel, callOptions);
    }

    /**
     */
    public SB.grpc.APILightsResponse lightswitch(SB.grpc.SwitchRequest request) {
      return blockingUnaryCall(
          getChannel(), getLightswitchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<SB.grpc.APISwitchOffAllLights> lightswitchall(
        SB.grpc.SwitchOffAllLights request) {
      return blockingServerStreamingCall(
          getChannel(), getLightswitchallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class lightsFutureStub extends io.grpc.stub.AbstractStub<lightsFutureStub> {
    private lightsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private lightsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected lightsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new lightsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.APILightsResponse> lightswitch(
        SB.grpc.SwitchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLightswitchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIGHTSWITCH = 0;
  private static final int METHODID_LIGHTSWITCHALL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final lightsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(lightsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIGHTSWITCH:
          serviceImpl.lightswitch((SB.grpc.SwitchRequest) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APILightsResponse>) responseObserver);
          break;
        case METHODID_LIGHTSWITCHALL:
          serviceImpl.lightswitchall((SB.grpc.SwitchOffAllLights) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APISwitchOffAllLights>) responseObserver);
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

  private static abstract class lightsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    lightsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SB.grpc.Lights.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("lights");
    }
  }

  private static final class lightsFileDescriptorSupplier
      extends lightsBaseDescriptorSupplier {
    lightsFileDescriptorSupplier() {}
  }

  private static final class lightsMethodDescriptorSupplier
      extends lightsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    lightsMethodDescriptorSupplier(String methodName) {
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
      synchronized (lightsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new lightsFileDescriptorSupplier())
              .addMethod(getLightswitchMethod())
              .addMethod(getLightswitchallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
