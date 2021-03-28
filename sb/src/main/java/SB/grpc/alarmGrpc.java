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
    comments = "Source: alarm.proto")
public final class alarmGrpc {

  private alarmGrpc() {}

  public static final String SERVICE_NAME = "alarm";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SB.grpc.AlarmOnRequest,
      SB.grpc.APIAlarmOnResponse> getAlarmonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "alarmon",
      requestType = SB.grpc.AlarmOnRequest.class,
      responseType = SB.grpc.APIAlarmOnResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.AlarmOnRequest,
      SB.grpc.APIAlarmOnResponse> getAlarmonMethod() {
    io.grpc.MethodDescriptor<SB.grpc.AlarmOnRequest, SB.grpc.APIAlarmOnResponse> getAlarmonMethod;
    if ((getAlarmonMethod = alarmGrpc.getAlarmonMethod) == null) {
      synchronized (alarmGrpc.class) {
        if ((getAlarmonMethod = alarmGrpc.getAlarmonMethod) == null) {
          alarmGrpc.getAlarmonMethod = getAlarmonMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.AlarmOnRequest, SB.grpc.APIAlarmOnResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "alarm", "alarmon"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.AlarmOnRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APIAlarmOnResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new alarmMethodDescriptorSupplier("alarmon"))
                  .build();
          }
        }
     }
     return getAlarmonMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SB.grpc.AlarmOffRequest,
      SB.grpc.APIAlarmOffResponse> getAlarmoffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "alarmoff",
      requestType = SB.grpc.AlarmOffRequest.class,
      responseType = SB.grpc.APIAlarmOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SB.grpc.AlarmOffRequest,
      SB.grpc.APIAlarmOffResponse> getAlarmoffMethod() {
    io.grpc.MethodDescriptor<SB.grpc.AlarmOffRequest, SB.grpc.APIAlarmOffResponse> getAlarmoffMethod;
    if ((getAlarmoffMethod = alarmGrpc.getAlarmoffMethod) == null) {
      synchronized (alarmGrpc.class) {
        if ((getAlarmoffMethod = alarmGrpc.getAlarmoffMethod) == null) {
          alarmGrpc.getAlarmoffMethod = getAlarmoffMethod = 
              io.grpc.MethodDescriptor.<SB.grpc.AlarmOffRequest, SB.grpc.APIAlarmOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "alarm", "alarmoff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.AlarmOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SB.grpc.APIAlarmOffResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new alarmMethodDescriptorSupplier("alarmoff"))
                  .build();
          }
        }
     }
     return getAlarmoffMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static alarmStub newStub(io.grpc.Channel channel) {
    return new alarmStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static alarmBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new alarmBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static alarmFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new alarmFutureStub(channel);
  }

  /**
   */
  public static abstract class alarmImplBase implements io.grpc.BindableService {

    /**
     */
    public void alarmon(SB.grpc.AlarmOnRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOnResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAlarmonMethod(), responseObserver);
    }

    /**
     */
    public void alarmoff(SB.grpc.AlarmOffRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOffResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAlarmoffMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAlarmonMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.AlarmOnRequest,
                SB.grpc.APIAlarmOnResponse>(
                  this, METHODID_ALARMON)))
          .addMethod(
            getAlarmoffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SB.grpc.AlarmOffRequest,
                SB.grpc.APIAlarmOffResponse>(
                  this, METHODID_ALARMOFF)))
          .build();
    }
  }

  /**
   */
  public static final class alarmStub extends io.grpc.stub.AbstractStub<alarmStub> {
    private alarmStub(io.grpc.Channel channel) {
      super(channel);
    }

    private alarmStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected alarmStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new alarmStub(channel, callOptions);
    }

    /**
     */
    public void alarmon(SB.grpc.AlarmOnRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOnResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAlarmonMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void alarmoff(SB.grpc.AlarmOffRequest request,
        io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOffResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAlarmoffMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class alarmBlockingStub extends io.grpc.stub.AbstractStub<alarmBlockingStub> {
    private alarmBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private alarmBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected alarmBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new alarmBlockingStub(channel, callOptions);
    }

    /**
     */
    public SB.grpc.APIAlarmOnResponse alarmon(SB.grpc.AlarmOnRequest request) {
      return blockingUnaryCall(
          getChannel(), getAlarmonMethod(), getCallOptions(), request);
    }

    /**
     */
    public SB.grpc.APIAlarmOffResponse alarmoff(SB.grpc.AlarmOffRequest request) {
      return blockingUnaryCall(
          getChannel(), getAlarmoffMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class alarmFutureStub extends io.grpc.stub.AbstractStub<alarmFutureStub> {
    private alarmFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private alarmFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected alarmFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new alarmFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.APIAlarmOnResponse> alarmon(
        SB.grpc.AlarmOnRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAlarmonMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SB.grpc.APIAlarmOffResponse> alarmoff(
        SB.grpc.AlarmOffRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAlarmoffMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALARMON = 0;
  private static final int METHODID_ALARMOFF = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final alarmImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(alarmImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ALARMON:
          serviceImpl.alarmon((SB.grpc.AlarmOnRequest) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOnResponse>) responseObserver);
          break;
        case METHODID_ALARMOFF:
          serviceImpl.alarmoff((SB.grpc.AlarmOffRequest) request,
              (io.grpc.stub.StreamObserver<SB.grpc.APIAlarmOffResponse>) responseObserver);
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

  private static abstract class alarmBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    alarmBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SB.grpc.Alarm.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("alarm");
    }
  }

  private static final class alarmFileDescriptorSupplier
      extends alarmBaseDescriptorSupplier {
    alarmFileDescriptorSupplier() {}
  }

  private static final class alarmMethodDescriptorSupplier
      extends alarmBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    alarmMethodDescriptorSupplier(String methodName) {
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
      synchronized (alarmGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new alarmFileDescriptorSupplier())
              .addMethod(getAlarmonMethod())
              .addMethod(getAlarmoffMethod())
              .build();
        }
      }
    }
    return result;
  }
}
