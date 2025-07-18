// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code FollowInfo}
 */
public final class FollowInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:FollowInfo)
    FollowInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FollowInfo.newBuilder() to construct.
  private FollowInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FollowInfo() {
    remarkName_ = "";
    followerCountStr_ = "";
    followingCountStr_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new FollowInfo();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_FollowInfo_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_FollowInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.class, org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.Builder.class);
  }

  public static final int FOLLOWINGCOUNT_FIELD_NUMBER = 1;
  private long followingCount_ = 0L;
  /**
   * <code>uint64 followingCount = 1;</code>
   * @return The followingCount.
   */
  @Override
  public long getFollowingCount() {
    return followingCount_;
  }

  public static final int FOLLOWERCOUNT_FIELD_NUMBER = 2;
  private long followerCount_ = 0L;
  /**
   * <code>uint64 followerCount = 2;</code>
   * @return The followerCount.
   */
  @Override
  public long getFollowerCount() {
    return followerCount_;
  }

  public static final int FOLLOWSTATUS_FIELD_NUMBER = 3;
  private long followStatus_ = 0L;
  /**
   * <code>uint64 followStatus = 3;</code>
   * @return The followStatus.
   */
  @Override
  public long getFollowStatus() {
    return followStatus_;
  }

  public static final int PUSHSTATUS_FIELD_NUMBER = 4;
  private long pushStatus_ = 0L;
  /**
   * <code>uint64 pushStatus = 4;</code>
   * @return The pushStatus.
   */
  @Override
  public long getPushStatus() {
    return pushStatus_;
  }

  public static final int REMARKNAME_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private volatile Object remarkName_ = "";
  /**
   * <code>string remarkName = 5;</code>
   * @return The remarkName.
   */
  @Override
  public String getRemarkName() {
    Object ref = remarkName_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      remarkName_ = s;
      return s;
    }
  }
  /**
   * <code>string remarkName = 5;</code>
   * @return The bytes for remarkName.
   */
  @Override
  public com.google.protobuf.ByteString
      getRemarkNameBytes() {
    Object ref = remarkName_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      remarkName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FOLLOWERCOUNTSTR_FIELD_NUMBER = 6;
  @SuppressWarnings("serial")
  private volatile Object followerCountStr_ = "";
  /**
   * <code>string followerCountStr = 6;</code>
   * @return The followerCountStr.
   */
  @Override
  public String getFollowerCountStr() {
    Object ref = followerCountStr_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      followerCountStr_ = s;
      return s;
    }
  }
  /**
   * <code>string followerCountStr = 6;</code>
   * @return The bytes for followerCountStr.
   */
  @Override
  public com.google.protobuf.ByteString
      getFollowerCountStrBytes() {
    Object ref = followerCountStr_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      followerCountStr_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FOLLOWINGCOUNTSTR_FIELD_NUMBER = 7;
  @SuppressWarnings("serial")
  private volatile Object followingCountStr_ = "";
  /**
   * <code>string followingCountStr = 7;</code>
   * @return The followingCountStr.
   */
  @Override
  public String getFollowingCountStr() {
    Object ref = followingCountStr_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      followingCountStr_ = s;
      return s;
    }
  }
  /**
   * <code>string followingCountStr = 7;</code>
   * @return The bytes for followingCountStr.
   */
  @Override
  public com.google.protobuf.ByteString
      getFollowingCountStrBytes() {
    Object ref = followingCountStr_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      followingCountStr_ = b;
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
    if (followingCount_ != 0L) {
      output.writeUInt64(1, followingCount_);
    }
    if (followerCount_ != 0L) {
      output.writeUInt64(2, followerCount_);
    }
    if (followStatus_ != 0L) {
      output.writeUInt64(3, followStatus_);
    }
    if (pushStatus_ != 0L) {
      output.writeUInt64(4, pushStatus_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(remarkName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, remarkName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(followerCountStr_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, followerCountStr_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(followingCountStr_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, followingCountStr_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (followingCount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, followingCount_);
    }
    if (followerCount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, followerCount_);
    }
    if (followStatus_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, followStatus_);
    }
    if (pushStatus_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, pushStatus_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(remarkName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, remarkName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(followerCountStr_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, followerCountStr_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(followingCountStr_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, followingCountStr_);
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
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo other = (org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo) obj;

    if (getFollowingCount()
        != other.getFollowingCount()) return false;
    if (getFollowerCount()
        != other.getFollowerCount()) return false;
    if (getFollowStatus()
        != other.getFollowStatus()) return false;
    if (getPushStatus()
        != other.getPushStatus()) return false;
    if (!getRemarkName()
        .equals(other.getRemarkName())) return false;
    if (!getFollowerCountStr()
        .equals(other.getFollowerCountStr())) return false;
    if (!getFollowingCountStr()
        .equals(other.getFollowingCountStr())) return false;
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
    hash = (37 * hash) + FOLLOWINGCOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFollowingCount());
    hash = (37 * hash) + FOLLOWERCOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFollowerCount());
    hash = (37 * hash) + FOLLOWSTATUS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFollowStatus());
    hash = (37 * hash) + PUSHSTATUS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPushStatus());
    hash = (37 * hash) + REMARKNAME_FIELD_NUMBER;
    hash = (53 * hash) + getRemarkName().hashCode();
    hash = (37 * hash) + FOLLOWERCOUNTSTR_FIELD_NUMBER;
    hash = (53 * hash) + getFollowerCountStr().hashCode();
    hash = (37 * hash) + FOLLOWINGCOUNTSTR_FIELD_NUMBER;
    hash = (53 * hash) + getFollowingCountStr().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo parseFrom(
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
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo prototype) {
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
   * Protobuf type {@code FollowInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FollowInfo)
      org.caesar.crawler.live.douyin.codec.protobuf.FollowInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_FollowInfo_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_FollowInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.class, org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.newBuilder()
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
      followingCount_ = 0L;
      followerCount_ = 0L;
      followStatus_ = 0L;
      pushStatus_ = 0L;
      remarkName_ = "";
      followerCountStr_ = "";
      followingCountStr_ = "";
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_FollowInfo_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo build() {
      org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo result = new org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.followingCount_ = followingCount_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.followerCount_ = followerCount_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.followStatus_ = followStatus_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.pushStatus_ = pushStatus_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.remarkName_ = remarkName_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.followerCountStr_ = followerCountStr_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.followingCountStr_ = followingCountStr_;
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
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo.getDefaultInstance()) return this;
      if (other.getFollowingCount() != 0L) {
        setFollowingCount(other.getFollowingCount());
      }
      if (other.getFollowerCount() != 0L) {
        setFollowerCount(other.getFollowerCount());
      }
      if (other.getFollowStatus() != 0L) {
        setFollowStatus(other.getFollowStatus());
      }
      if (other.getPushStatus() != 0L) {
        setPushStatus(other.getPushStatus());
      }
      if (!other.getRemarkName().isEmpty()) {
        remarkName_ = other.remarkName_;
        bitField0_ |= 0x00000010;
        onChanged();
      }
      if (!other.getFollowerCountStr().isEmpty()) {
        followerCountStr_ = other.followerCountStr_;
        bitField0_ |= 0x00000020;
        onChanged();
      }
      if (!other.getFollowingCountStr().isEmpty()) {
        followingCountStr_ = other.followingCountStr_;
        bitField0_ |= 0x00000040;
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
            case 8: {
              followingCount_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              followerCount_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              followStatus_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              pushStatus_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 42: {
              remarkName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            case 50: {
              followerCountStr_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000020;
              break;
            } // case 50
            case 58: {
              followingCountStr_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000040;
              break;
            } // case 58
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

    private long followingCount_ ;
    /**
     * <code>uint64 followingCount = 1;</code>
     * @return The followingCount.
     */
    @Override
    public long getFollowingCount() {
      return followingCount_;
    }
    /**
     * <code>uint64 followingCount = 1;</code>
     * @param value The followingCount to set.
     * @return This builder for chaining.
     */
    public Builder setFollowingCount(long value) {

      followingCount_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 followingCount = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFollowingCount() {
      bitField0_ = (bitField0_ & ~0x00000001);
      followingCount_ = 0L;
      onChanged();
      return this;
    }

    private long followerCount_ ;
    /**
     * <code>uint64 followerCount = 2;</code>
     * @return The followerCount.
     */
    @Override
    public long getFollowerCount() {
      return followerCount_;
    }
    /**
     * <code>uint64 followerCount = 2;</code>
     * @param value The followerCount to set.
     * @return This builder for chaining.
     */
    public Builder setFollowerCount(long value) {

      followerCount_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 followerCount = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearFollowerCount() {
      bitField0_ = (bitField0_ & ~0x00000002);
      followerCount_ = 0L;
      onChanged();
      return this;
    }

    private long followStatus_ ;
    /**
     * <code>uint64 followStatus = 3;</code>
     * @return The followStatus.
     */
    @Override
    public long getFollowStatus() {
      return followStatus_;
    }
    /**
     * <code>uint64 followStatus = 3;</code>
     * @param value The followStatus to set.
     * @return This builder for chaining.
     */
    public Builder setFollowStatus(long value) {

      followStatus_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 followStatus = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearFollowStatus() {
      bitField0_ = (bitField0_ & ~0x00000004);
      followStatus_ = 0L;
      onChanged();
      return this;
    }

    private long pushStatus_ ;
    /**
     * <code>uint64 pushStatus = 4;</code>
     * @return The pushStatus.
     */
    @Override
    public long getPushStatus() {
      return pushStatus_;
    }
    /**
     * <code>uint64 pushStatus = 4;</code>
     * @param value The pushStatus to set.
     * @return This builder for chaining.
     */
    public Builder setPushStatus(long value) {

      pushStatus_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 pushStatus = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPushStatus() {
      bitField0_ = (bitField0_ & ~0x00000008);
      pushStatus_ = 0L;
      onChanged();
      return this;
    }

    private Object remarkName_ = "";
    /**
     * <code>string remarkName = 5;</code>
     * @return The remarkName.
     */
    public String getRemarkName() {
      Object ref = remarkName_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        remarkName_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string remarkName = 5;</code>
     * @return The bytes for remarkName.
     */
    public com.google.protobuf.ByteString
        getRemarkNameBytes() {
      Object ref = remarkName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        remarkName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string remarkName = 5;</code>
     * @param value The remarkName to set.
     * @return This builder for chaining.
     */
    public Builder setRemarkName(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      remarkName_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>string remarkName = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearRemarkName() {
      remarkName_ = getDefaultInstance().getRemarkName();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }
    /**
     * <code>string remarkName = 5;</code>
     * @param value The bytes for remarkName to set.
     * @return This builder for chaining.
     */
    public Builder setRemarkNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      remarkName_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }

    private Object followerCountStr_ = "";
    /**
     * <code>string followerCountStr = 6;</code>
     * @return The followerCountStr.
     */
    public String getFollowerCountStr() {
      Object ref = followerCountStr_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        followerCountStr_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string followerCountStr = 6;</code>
     * @return The bytes for followerCountStr.
     */
    public com.google.protobuf.ByteString
        getFollowerCountStrBytes() {
      Object ref = followerCountStr_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        followerCountStr_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string followerCountStr = 6;</code>
     * @param value The followerCountStr to set.
     * @return This builder for chaining.
     */
    public Builder setFollowerCountStr(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      followerCountStr_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>string followerCountStr = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearFollowerCountStr() {
      followerCountStr_ = getDefaultInstance().getFollowerCountStr();
      bitField0_ = (bitField0_ & ~0x00000020);
      onChanged();
      return this;
    }
    /**
     * <code>string followerCountStr = 6;</code>
     * @param value The bytes for followerCountStr to set.
     * @return This builder for chaining.
     */
    public Builder setFollowerCountStrBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      followerCountStr_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }

    private Object followingCountStr_ = "";
    /**
     * <code>string followingCountStr = 7;</code>
     * @return The followingCountStr.
     */
    public String getFollowingCountStr() {
      Object ref = followingCountStr_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        followingCountStr_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string followingCountStr = 7;</code>
     * @return The bytes for followingCountStr.
     */
    public com.google.protobuf.ByteString
        getFollowingCountStrBytes() {
      Object ref = followingCountStr_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        followingCountStr_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string followingCountStr = 7;</code>
     * @param value The followingCountStr to set.
     * @return This builder for chaining.
     */
    public Builder setFollowingCountStr(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      followingCountStr_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>string followingCountStr = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearFollowingCountStr() {
      followingCountStr_ = getDefaultInstance().getFollowingCountStr();
      bitField0_ = (bitField0_ & ~0x00000040);
      onChanged();
      return this;
    }
    /**
     * <code>string followingCountStr = 7;</code>
     * @param value The bytes for followingCountStr to set.
     * @return This builder for chaining.
     */
    public Builder setFollowingCountStrBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      followingCountStr_ = value;
      bitField0_ |= 0x00000040;
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


    // @@protoc_insertion_point(builder_scope:FollowInfo)
  }

  // @@protoc_insertion_point(class_scope:FollowInfo)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FollowInfo>
      PARSER = new com.google.protobuf.AbstractParser<FollowInfo>() {
    @Override
    public FollowInfo parsePartialFrom(
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

  public static com.google.protobuf.Parser<FollowInfo> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<FollowInfo> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.FollowInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

