// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code TextPieceHeart}
 */
public final class TextPieceHeart extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TextPieceHeart)
    TextPieceHeartOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TextPieceHeart.newBuilder() to construct.
  private TextPieceHeart(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TextPieceHeart() {
    color_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new TextPieceHeart();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_TextPieceHeart_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_TextPieceHeart_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.class, org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.Builder.class);
  }

  public static final int COLOR_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile Object color_ = "";
  /**
   * <code>string color = 1;</code>
   * @return The color.
   */
  @Override
  public String getColor() {
    Object ref = color_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      color_ = s;
      return s;
    }
  }
  /**
   * <code>string color = 1;</code>
   * @return The bytes for color.
   */
  @Override
  public com.google.protobuf.ByteString
      getColorBytes() {
    Object ref = color_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      color_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(color_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, color_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(color_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, color_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart other = (org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart) obj;

    if (!getColor()
        .equals(other.getColor())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + COLOR_FIELD_NUMBER;
    hash = (53 * hash) + getColor().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code TextPieceHeart}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TextPieceHeart)
      org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeartOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_TextPieceHeart_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_TextPieceHeart_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.class, org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      color_ = "";
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_TextPieceHeart_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart build() {
      org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart result = new org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.color_ = color_;
      }
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart.getDefaultInstance()) return this;
      if (!other.getColor().isEmpty()) {
        color_ = other.color_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              color_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private Object color_ = "";
    /**
     * <code>string color = 1;</code>
     * @return The color.
     */
    public String getColor() {
      Object ref = color_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        color_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string color = 1;</code>
     * @return The bytes for color.
     */
    public com.google.protobuf.ByteString
        getColorBytes() {
      Object ref = color_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        color_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string color = 1;</code>
     * @param value The color to set.
     * @return This builder for chaining.
     */
    public Builder setColor(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      color_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string color = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearColor() {
      color_ = getDefaultInstance().getColor();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string color = 1;</code>
     * @param value The bytes for color to set.
     * @return This builder for chaining.
     */
    public Builder setColorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      color_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:TextPieceHeart)
  }

  // @@protoc_insertion_point(class_scope:TextPieceHeart)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TextPieceHeart>
      PARSER = new com.google.protobuf.AbstractParser<TextPieceHeart>() {
    @Override
    public TextPieceHeart parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<TextPieceHeart> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<TextPieceHeart> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.TextPieceHeart getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

