// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code MatchAgainstScoreMessage}
 */
public final class MatchAgainstScoreMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:MatchAgainstScoreMessage)
    MatchAgainstScoreMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MatchAgainstScoreMessage.newBuilder() to construct.
  private MatchAgainstScoreMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MatchAgainstScoreMessage() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new MatchAgainstScoreMessage();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_MatchAgainstScoreMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.class, org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.Builder.class);
  }

  private int bitField0_;
  public static final int COMMON_FIELD_NUMBER = 1;
  private org.caesar.crawler.live.douyin.codec.protobuf.Common common_;
  /**
   * <code>.Common common = 1;</code>
   * @return Whether the common field is set.
   */
  @Override
  public boolean hasCommon() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.Common common = 1;</code>
   * @return The common.
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.Common getCommon() {
    return common_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Common.getDefaultInstance() : common_;
  }
  /**
   * <code>.Common common = 1;</code>
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder getCommonOrBuilder() {
    return common_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Common.getDefaultInstance() : common_;
  }

  public static final int AGAINST_FIELD_NUMBER = 2;
  private org.caesar.crawler.live.douyin.codec.protobuf.Against against_;
  /**
   * <code>.Against against = 2;</code>
   * @return Whether the against field is set.
   */
  @Override
  public boolean hasAgainst() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>.Against against = 2;</code>
   * @return The against.
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.Against getAgainst() {
    return against_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Against.getDefaultInstance() : against_;
  }
  /**
   * <code>.Against against = 2;</code>
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.AgainstOrBuilder getAgainstOrBuilder() {
    return against_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Against.getDefaultInstance() : against_;
  }

  public static final int MATCHSTATUS_FIELD_NUMBER = 3;
  private int matchStatus_ = 0;
  /**
   * <code>uint32 matchStatus = 3;</code>
   * @return The matchStatus.
   */
  @Override
  public int getMatchStatus() {
    return matchStatus_;
  }

  public static final int DISPLAYSTATUS_FIELD_NUMBER = 4;
  private int displayStatus_ = 0;
  /**
   * <code>uint32 displayStatus = 4;</code>
   * @return The displayStatus.
   */
  @Override
  public int getDisplayStatus() {
    return displayStatus_;
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
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getCommon());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(2, getAgainst());
    }
    if (matchStatus_ != 0) {
      output.writeUInt32(3, matchStatus_);
    }
    if (displayStatus_ != 0) {
      output.writeUInt32(4, displayStatus_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getCommon());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAgainst());
    }
    if (matchStatus_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(3, matchStatus_);
    }
    if (displayStatus_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(4, displayStatus_);
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
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage other = (org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage) obj;

    if (hasCommon() != other.hasCommon()) return false;
    if (hasCommon()) {
      if (!getCommon()
          .equals(other.getCommon())) return false;
    }
    if (hasAgainst() != other.hasAgainst()) return false;
    if (hasAgainst()) {
      if (!getAgainst()
          .equals(other.getAgainst())) return false;
    }
    if (getMatchStatus()
        != other.getMatchStatus()) return false;
    if (getDisplayStatus()
        != other.getDisplayStatus()) return false;
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
    if (hasCommon()) {
      hash = (37 * hash) + COMMON_FIELD_NUMBER;
      hash = (53 * hash) + getCommon().hashCode();
    }
    if (hasAgainst()) {
      hash = (37 * hash) + AGAINST_FIELD_NUMBER;
      hash = (53 * hash) + getAgainst().hashCode();
    }
    hash = (37 * hash) + MATCHSTATUS_FIELD_NUMBER;
    hash = (53 * hash) + getMatchStatus();
    hash = (37 * hash) + DISPLAYSTATUS_FIELD_NUMBER;
    hash = (53 * hash) + getDisplayStatus();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage parseFrom(
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
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage prototype) {
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
   * Protobuf type {@code MatchAgainstScoreMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:MatchAgainstScoreMessage)
      org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_MatchAgainstScoreMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.class, org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getAgainstFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      common_ = null;
      if (commonBuilder_ != null) {
        commonBuilder_.dispose();
        commonBuilder_ = null;
      }
      against_ = null;
      if (againstBuilder_ != null) {
        againstBuilder_.dispose();
        againstBuilder_ = null;
      }
      matchStatus_ = 0;
      displayStatus_ = 0;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage build() {
      org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage result = new org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.common_ = commonBuilder_ == null
            ? common_
            : commonBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.against_ = againstBuilder_ == null
            ? against_
            : againstBuilder_.build();
        to_bitField0_ |= 0x00000002;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.matchStatus_ = matchStatus_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.displayStatus_ = displayStatus_;
      }
      result.bitField0_ |= to_bitField0_;
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
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage.getDefaultInstance()) return this;
      if (other.hasCommon()) {
        mergeCommon(other.getCommon());
      }
      if (other.hasAgainst()) {
        mergeAgainst(other.getAgainst());
      }
      if (other.getMatchStatus() != 0) {
        setMatchStatus(other.getMatchStatus());
      }
      if (other.getDisplayStatus() != 0) {
        setDisplayStatus(other.getDisplayStatus());
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
              input.readMessage(
                  getCommonFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              input.readMessage(
                  getAgainstFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              matchStatus_ = input.readUInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              displayStatus_ = input.readUInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
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

    private org.caesar.crawler.live.douyin.codec.protobuf.Common common_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.Common, org.caesar.crawler.live.douyin.codec.protobuf.Common.Builder, org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder> commonBuilder_;
    /**
     * <code>.Common common = 1;</code>
     * @return Whether the common field is set.
     */
    public boolean hasCommon() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.Common common = 1;</code>
     * @return The common.
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.Common getCommon() {
      if (commonBuilder_ == null) {
        return common_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Common.getDefaultInstance() : common_;
      } else {
        return commonBuilder_.getMessage();
      }
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public Builder setCommon(org.caesar.crawler.live.douyin.codec.protobuf.Common value) {
      if (commonBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        common_ = value;
      } else {
        commonBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public Builder setCommon(
        org.caesar.crawler.live.douyin.codec.protobuf.Common.Builder builderForValue) {
      if (commonBuilder_ == null) {
        common_ = builderForValue.build();
      } else {
        commonBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public Builder mergeCommon(org.caesar.crawler.live.douyin.codec.protobuf.Common value) {
      if (commonBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          common_ != null &&
          common_ != org.caesar.crawler.live.douyin.codec.protobuf.Common.getDefaultInstance()) {
          getCommonBuilder().mergeFrom(value);
        } else {
          common_ = value;
        }
      } else {
        commonBuilder_.mergeFrom(value);
      }
      if (common_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public Builder clearCommon() {
      bitField0_ = (bitField0_ & ~0x00000001);
      common_ = null;
      if (commonBuilder_ != null) {
        commonBuilder_.dispose();
        commonBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.Common.Builder getCommonBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getCommonFieldBuilder().getBuilder();
    }
    /**
     * <code>.Common common = 1;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder getCommonOrBuilder() {
      if (commonBuilder_ != null) {
        return commonBuilder_.getMessageOrBuilder();
      } else {
        return common_ == null ?
            org.caesar.crawler.live.douyin.codec.protobuf.Common.getDefaultInstance() : common_;
      }
    }
    /**
     * <code>.Common common = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.Common, org.caesar.crawler.live.douyin.codec.protobuf.Common.Builder, org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder>
        getCommonFieldBuilder() {
      if (commonBuilder_ == null) {
        commonBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.caesar.crawler.live.douyin.codec.protobuf.Common, org.caesar.crawler.live.douyin.codec.protobuf.Common.Builder, org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder>(
                getCommon(),
                getParentForChildren(),
                isClean());
        common_ = null;
      }
      return commonBuilder_;
    }

    private org.caesar.crawler.live.douyin.codec.protobuf.Against against_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.Against, org.caesar.crawler.live.douyin.codec.protobuf.Against.Builder, org.caesar.crawler.live.douyin.codec.protobuf.AgainstOrBuilder> againstBuilder_;
    /**
     * <code>.Against against = 2;</code>
     * @return Whether the against field is set.
     */
    public boolean hasAgainst() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.Against against = 2;</code>
     * @return The against.
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.Against getAgainst() {
      if (againstBuilder_ == null) {
        return against_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.Against.getDefaultInstance() : against_;
      } else {
        return againstBuilder_.getMessage();
      }
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public Builder setAgainst(org.caesar.crawler.live.douyin.codec.protobuf.Against value) {
      if (againstBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        against_ = value;
      } else {
        againstBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public Builder setAgainst(
        org.caesar.crawler.live.douyin.codec.protobuf.Against.Builder builderForValue) {
      if (againstBuilder_ == null) {
        against_ = builderForValue.build();
      } else {
        againstBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public Builder mergeAgainst(org.caesar.crawler.live.douyin.codec.protobuf.Against value) {
      if (againstBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          against_ != null &&
          against_ != org.caesar.crawler.live.douyin.codec.protobuf.Against.getDefaultInstance()) {
          getAgainstBuilder().mergeFrom(value);
        } else {
          against_ = value;
        }
      } else {
        againstBuilder_.mergeFrom(value);
      }
      if (against_ != null) {
        bitField0_ |= 0x00000002;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public Builder clearAgainst() {
      bitField0_ = (bitField0_ & ~0x00000002);
      against_ = null;
      if (againstBuilder_ != null) {
        againstBuilder_.dispose();
        againstBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.Against.Builder getAgainstBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getAgainstFieldBuilder().getBuilder();
    }
    /**
     * <code>.Against against = 2;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.AgainstOrBuilder getAgainstOrBuilder() {
      if (againstBuilder_ != null) {
        return againstBuilder_.getMessageOrBuilder();
      } else {
        return against_ == null ?
            org.caesar.crawler.live.douyin.codec.protobuf.Against.getDefaultInstance() : against_;
      }
    }
    /**
     * <code>.Against against = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.Against, org.caesar.crawler.live.douyin.codec.protobuf.Against.Builder, org.caesar.crawler.live.douyin.codec.protobuf.AgainstOrBuilder>
        getAgainstFieldBuilder() {
      if (againstBuilder_ == null) {
        againstBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.caesar.crawler.live.douyin.codec.protobuf.Against, org.caesar.crawler.live.douyin.codec.protobuf.Against.Builder, org.caesar.crawler.live.douyin.codec.protobuf.AgainstOrBuilder>(
                getAgainst(),
                getParentForChildren(),
                isClean());
        against_ = null;
      }
      return againstBuilder_;
    }

    private int matchStatus_ ;
    /**
     * <code>uint32 matchStatus = 3;</code>
     * @return The matchStatus.
     */
    @Override
    public int getMatchStatus() {
      return matchStatus_;
    }
    /**
     * <code>uint32 matchStatus = 3;</code>
     * @param value The matchStatus to set.
     * @return This builder for chaining.
     */
    public Builder setMatchStatus(int value) {

      matchStatus_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 matchStatus = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMatchStatus() {
      bitField0_ = (bitField0_ & ~0x00000004);
      matchStatus_ = 0;
      onChanged();
      return this;
    }

    private int displayStatus_ ;
    /**
     * <code>uint32 displayStatus = 4;</code>
     * @return The displayStatus.
     */
    @Override
    public int getDisplayStatus() {
      return displayStatus_;
    }
    /**
     * <code>uint32 displayStatus = 4;</code>
     * @param value The displayStatus to set.
     * @return This builder for chaining.
     */
    public Builder setDisplayStatus(int value) {

      displayStatus_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 displayStatus = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearDisplayStatus() {
      bitField0_ = (bitField0_ & ~0x00000008);
      displayStatus_ = 0;
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


    // @@protoc_insertion_point(builder_scope:MatchAgainstScoreMessage)
  }

  // @@protoc_insertion_point(class_scope:MatchAgainstScoreMessage)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MatchAgainstScoreMessage>
      PARSER = new com.google.protobuf.AbstractParser<MatchAgainstScoreMessage>() {
    @Override
    public MatchAgainstScoreMessage parsePartialFrom(
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

  public static com.google.protobuf.Parser<MatchAgainstScoreMessage> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<MatchAgainstScoreMessage> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.MatchAgainstScoreMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

