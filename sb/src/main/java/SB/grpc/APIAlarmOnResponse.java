// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: alarm.proto

package SB.grpc;

/**
 * Protobuf type {@code APIAlarmOnResponse}
 */
public  final class APIAlarmOnResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:APIAlarmOnResponse)
    APIAlarmOnResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use APIAlarmOnResponse.newBuilder() to construct.
  private APIAlarmOnResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private APIAlarmOnResponse() {
    alarmonresmessage_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private APIAlarmOnResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            alarmonresmessage_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return SB.grpc.Alarm.internal_static_APIAlarmOnResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return SB.grpc.Alarm.internal_static_APIAlarmOnResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            SB.grpc.APIAlarmOnResponse.class, SB.grpc.APIAlarmOnResponse.Builder.class);
  }

  public static final int ALARMONRESMESSAGE_FIELD_NUMBER = 1;
  private volatile java.lang.Object alarmonresmessage_;
  /**
   * <code>string alarmonresmessage = 1;</code>
   */
  public java.lang.String getAlarmonresmessage() {
    java.lang.Object ref = alarmonresmessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      alarmonresmessage_ = s;
      return s;
    }
  }
  /**
   * <code>string alarmonresmessage = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAlarmonresmessageBytes() {
    java.lang.Object ref = alarmonresmessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      alarmonresmessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getAlarmonresmessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, alarmonresmessage_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAlarmonresmessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, alarmonresmessage_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof SB.grpc.APIAlarmOnResponse)) {
      return super.equals(obj);
    }
    SB.grpc.APIAlarmOnResponse other = (SB.grpc.APIAlarmOnResponse) obj;

    boolean result = true;
    result = result && getAlarmonresmessage()
        .equals(other.getAlarmonresmessage());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALARMONRESMESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getAlarmonresmessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static SB.grpc.APIAlarmOnResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static SB.grpc.APIAlarmOnResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static SB.grpc.APIAlarmOnResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SB.grpc.APIAlarmOnResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(SB.grpc.APIAlarmOnResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code APIAlarmOnResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:APIAlarmOnResponse)
      SB.grpc.APIAlarmOnResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SB.grpc.Alarm.internal_static_APIAlarmOnResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SB.grpc.Alarm.internal_static_APIAlarmOnResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SB.grpc.APIAlarmOnResponse.class, SB.grpc.APIAlarmOnResponse.Builder.class);
    }

    // Construct using SB.grpc.APIAlarmOnResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      alarmonresmessage_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return SB.grpc.Alarm.internal_static_APIAlarmOnResponse_descriptor;
    }

    @java.lang.Override
    public SB.grpc.APIAlarmOnResponse getDefaultInstanceForType() {
      return SB.grpc.APIAlarmOnResponse.getDefaultInstance();
    }

    @java.lang.Override
    public SB.grpc.APIAlarmOnResponse build() {
      SB.grpc.APIAlarmOnResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public SB.grpc.APIAlarmOnResponse buildPartial() {
      SB.grpc.APIAlarmOnResponse result = new SB.grpc.APIAlarmOnResponse(this);
      result.alarmonresmessage_ = alarmonresmessage_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof SB.grpc.APIAlarmOnResponse) {
        return mergeFrom((SB.grpc.APIAlarmOnResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(SB.grpc.APIAlarmOnResponse other) {
      if (other == SB.grpc.APIAlarmOnResponse.getDefaultInstance()) return this;
      if (!other.getAlarmonresmessage().isEmpty()) {
        alarmonresmessage_ = other.alarmonresmessage_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      SB.grpc.APIAlarmOnResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (SB.grpc.APIAlarmOnResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object alarmonresmessage_ = "";
    /**
     * <code>string alarmonresmessage = 1;</code>
     */
    public java.lang.String getAlarmonresmessage() {
      java.lang.Object ref = alarmonresmessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        alarmonresmessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string alarmonresmessage = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAlarmonresmessageBytes() {
      java.lang.Object ref = alarmonresmessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        alarmonresmessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string alarmonresmessage = 1;</code>
     */
    public Builder setAlarmonresmessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      alarmonresmessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string alarmonresmessage = 1;</code>
     */
    public Builder clearAlarmonresmessage() {
      
      alarmonresmessage_ = getDefaultInstance().getAlarmonresmessage();
      onChanged();
      return this;
    }
    /**
     * <code>string alarmonresmessage = 1;</code>
     */
    public Builder setAlarmonresmessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      alarmonresmessage_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:APIAlarmOnResponse)
  }

  // @@protoc_insertion_point(class_scope:APIAlarmOnResponse)
  private static final SB.grpc.APIAlarmOnResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new SB.grpc.APIAlarmOnResponse();
  }

  public static SB.grpc.APIAlarmOnResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<APIAlarmOnResponse>
      PARSER = new com.google.protobuf.AbstractParser<APIAlarmOnResponse>() {
    @java.lang.Override
    public APIAlarmOnResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new APIAlarmOnResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<APIAlarmOnResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<APIAlarmOnResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public SB.grpc.APIAlarmOnResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
