// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code RoomUserSeqMessageContributor}
 */
public final class RoomUserSeqMessageContributor extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RoomUserSeqMessageContributor)
    RoomUserSeqMessageContributorOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RoomUserSeqMessageContributor.newBuilder() to construct.
  private RoomUserSeqMessageContributor(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RoomUserSeqMessageContributor() {
    scoreDescription_ = "";
    exactlyScore_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new RoomUserSeqMessageContributor();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_RoomUserSeqMessageContributor_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_RoomUserSeqMessageContributor_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.class, org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.Builder.class);
  }

  private int bitField0_;
  public static final int SCORE_FIELD_NUMBER = 1;
  private long score_ = 0L;
  /**
   * <code>uint64 score = 1;</code>
   * @return The score.
   */
  @Override
  public long getScore() {
    return score_;
  }

  public static final int USER_FIELD_NUMBER = 2;
  private org.caesar.crawler.live.douyin.codec.protobuf.User user_;
  /**
   * <code>.User user = 2;</code>
   * @return Whether the user field is set.
   */
  @Override
  public boolean hasUser() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.User user = 2;</code>
   * @return The user.
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.User getUser() {
    return user_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.User.getDefaultInstance() : user_;
  }
  /**
   * <code>.User user = 2;</code>
   */
  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder getUserOrBuilder() {
    return user_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.User.getDefaultInstance() : user_;
  }

  public static final int RANK_FIELD_NUMBER = 3;
  private long rank_ = 0L;
  /**
   * <code>uint64 rank = 3;</code>
   * @return The rank.
   */
  @Override
  public long getRank() {
    return rank_;
  }

  public static final int DELTA_FIELD_NUMBER = 4;
  private long delta_ = 0L;
  /**
   * <code>uint64 delta = 4;</code>
   * @return The delta.
   */
  @Override
  public long getDelta() {
    return delta_;
  }

  public static final int ISHIDDEN_FIELD_NUMBER = 5;
  private boolean isHidden_ = false;
  /**
   * <code>bool isHidden = 5;</code>
   * @return The isHidden.
   */
  @Override
  public boolean getIsHidden() {
    return isHidden_;
  }

  public static final int SCOREDESCRIPTION_FIELD_NUMBER = 6;
  @SuppressWarnings("serial")
  private volatile Object scoreDescription_ = "";
  /**
   * <code>string scoreDescription = 6;</code>
   * @return The scoreDescription.
   */
  @Override
  public String getScoreDescription() {
    Object ref = scoreDescription_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      scoreDescription_ = s;
      return s;
    }
  }
  /**
   * <code>string scoreDescription = 6;</code>
   * @return The bytes for scoreDescription.
   */
  @Override
  public com.google.protobuf.ByteString
      getScoreDescriptionBytes() {
    Object ref = scoreDescription_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      scoreDescription_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXACTLYSCORE_FIELD_NUMBER = 7;
  @SuppressWarnings("serial")
  private volatile Object exactlyScore_ = "";
  /**
   * <code>string exactlyScore = 7;</code>
   * @return The exactlyScore.
   */
  @Override
  public String getExactlyScore() {
    Object ref = exactlyScore_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      exactlyScore_ = s;
      return s;
    }
  }
  /**
   * <code>string exactlyScore = 7;</code>
   * @return The bytes for exactlyScore.
   */
  @Override
  public com.google.protobuf.ByteString
      getExactlyScoreBytes() {
    Object ref = exactlyScore_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      exactlyScore_ = b;
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
    if (score_ != 0L) {
      output.writeUInt64(1, score_);
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(2, getUser());
    }
    if (rank_ != 0L) {
      output.writeUInt64(3, rank_);
    }
    if (delta_ != 0L) {
      output.writeUInt64(4, delta_);
    }
    if (isHidden_ != false) {
      output.writeBool(5, isHidden_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(scoreDescription_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, scoreDescription_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(exactlyScore_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, exactlyScore_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (score_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, score_);
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getUser());
    }
    if (rank_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, rank_);
    }
    if (delta_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, delta_);
    }
    if (isHidden_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, isHidden_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(scoreDescription_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, scoreDescription_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(exactlyScore_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, exactlyScore_);
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
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor other = (org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor) obj;

    if (getScore()
        != other.getScore()) return false;
    if (hasUser() != other.hasUser()) return false;
    if (hasUser()) {
      if (!getUser()
          .equals(other.getUser())) return false;
    }
    if (getRank()
        != other.getRank()) return false;
    if (getDelta()
        != other.getDelta()) return false;
    if (getIsHidden()
        != other.getIsHidden()) return false;
    if (!getScoreDescription()
        .equals(other.getScoreDescription())) return false;
    if (!getExactlyScore()
        .equals(other.getExactlyScore())) return false;
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
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getScore());
    if (hasUser()) {
      hash = (37 * hash) + USER_FIELD_NUMBER;
      hash = (53 * hash) + getUser().hashCode();
    }
    hash = (37 * hash) + RANK_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRank());
    hash = (37 * hash) + DELTA_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDelta());
    hash = (37 * hash) + ISHIDDEN_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsHidden());
    hash = (37 * hash) + SCOREDESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getScoreDescription().hashCode();
    hash = (37 * hash) + EXACTLYSCORE_FIELD_NUMBER;
    hash = (53 * hash) + getExactlyScore().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor parseFrom(
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
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor prototype) {
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
   * Protobuf type {@code RoomUserSeqMessageContributor}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RoomUserSeqMessageContributor)
      org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributorOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_RoomUserSeqMessageContributor_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_RoomUserSeqMessageContributor_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.class, org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.newBuilder()
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
        getUserFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      score_ = 0L;
      user_ = null;
      if (userBuilder_ != null) {
        userBuilder_.dispose();
        userBuilder_ = null;
      }
      rank_ = 0L;
      delta_ = 0L;
      isHidden_ = false;
      scoreDescription_ = "";
      exactlyScore_ = "";
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_RoomUserSeqMessageContributor_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor build() {
      org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor result = new org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.score_ = score_;
      }
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.user_ = userBuilder_ == null
            ? user_
            : userBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.rank_ = rank_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.delta_ = delta_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.isHidden_ = isHidden_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.scoreDescription_ = scoreDescription_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.exactlyScore_ = exactlyScore_;
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
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor.getDefaultInstance()) return this;
      if (other.getScore() != 0L) {
        setScore(other.getScore());
      }
      if (other.hasUser()) {
        mergeUser(other.getUser());
      }
      if (other.getRank() != 0L) {
        setRank(other.getRank());
      }
      if (other.getDelta() != 0L) {
        setDelta(other.getDelta());
      }
      if (other.getIsHidden() != false) {
        setIsHidden(other.getIsHidden());
      }
      if (!other.getScoreDescription().isEmpty()) {
        scoreDescription_ = other.scoreDescription_;
        bitField0_ |= 0x00000020;
        onChanged();
      }
      if (!other.getExactlyScore().isEmpty()) {
        exactlyScore_ = other.exactlyScore_;
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
              score_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              input.readMessage(
                  getUserFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              rank_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              delta_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              isHidden_ = input.readBool();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 50: {
              scoreDescription_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000020;
              break;
            } // case 50
            case 58: {
              exactlyScore_ = input.readStringRequireUtf8();
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

    private long score_ ;
    /**
     * <code>uint64 score = 1;</code>
     * @return The score.
     */
    @Override
    public long getScore() {
      return score_;
    }
    /**
     * <code>uint64 score = 1;</code>
     * @param value The score to set.
     * @return This builder for chaining.
     */
    public Builder setScore(long value) {

      score_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 score = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearScore() {
      bitField0_ = (bitField0_ & ~0x00000001);
      score_ = 0L;
      onChanged();
      return this;
    }

    private org.caesar.crawler.live.douyin.codec.protobuf.User user_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.User, org.caesar.crawler.live.douyin.codec.protobuf.User.Builder, org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder> userBuilder_;
    /**
     * <code>.User user = 2;</code>
     * @return Whether the user field is set.
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.User user = 2;</code>
     * @return The user.
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.User getUser() {
      if (userBuilder_ == null) {
        return user_ == null ? org.caesar.crawler.live.douyin.codec.protobuf.User.getDefaultInstance() : user_;
      } else {
        return userBuilder_.getMessage();
      }
    }
    /**
     * <code>.User user = 2;</code>
     */
    public Builder setUser(org.caesar.crawler.live.douyin.codec.protobuf.User value) {
      if (userBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        user_ = value;
      } else {
        userBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 2;</code>
     */
    public Builder setUser(
        org.caesar.crawler.live.douyin.codec.protobuf.User.Builder builderForValue) {
      if (userBuilder_ == null) {
        user_ = builderForValue.build();
      } else {
        userBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 2;</code>
     */
    public Builder mergeUser(org.caesar.crawler.live.douyin.codec.protobuf.User value) {
      if (userBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          user_ != null &&
          user_ != org.caesar.crawler.live.douyin.codec.protobuf.User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          user_ = value;
        }
      } else {
        userBuilder_.mergeFrom(value);
      }
      if (user_ != null) {
        bitField0_ |= 0x00000002;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.User user = 2;</code>
     */
    public Builder clearUser() {
      bitField0_ = (bitField0_ & ~0x00000002);
      user_ = null;
      if (userBuilder_ != null) {
        userBuilder_.dispose();
        userBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.User user = 2;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.User.Builder getUserBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getUserFieldBuilder().getBuilder();
    }
    /**
     * <code>.User user = 2;</code>
     */
    public org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder getUserOrBuilder() {
      if (userBuilder_ != null) {
        return userBuilder_.getMessageOrBuilder();
      } else {
        return user_ == null ?
            org.caesar.crawler.live.douyin.codec.protobuf.User.getDefaultInstance() : user_;
      }
    }
    /**
     * <code>.User user = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.caesar.crawler.live.douyin.codec.protobuf.User, org.caesar.crawler.live.douyin.codec.protobuf.User.Builder, org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder>
        getUserFieldBuilder() {
      if (userBuilder_ == null) {
        userBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.caesar.crawler.live.douyin.codec.protobuf.User, org.caesar.crawler.live.douyin.codec.protobuf.User.Builder, org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder>(
                getUser(),
                getParentForChildren(),
                isClean());
        user_ = null;
      }
      return userBuilder_;
    }

    private long rank_ ;
    /**
     * <code>uint64 rank = 3;</code>
     * @return The rank.
     */
    @Override
    public long getRank() {
      return rank_;
    }
    /**
     * <code>uint64 rank = 3;</code>
     * @param value The rank to set.
     * @return This builder for chaining.
     */
    public Builder setRank(long value) {

      rank_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 rank = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearRank() {
      bitField0_ = (bitField0_ & ~0x00000004);
      rank_ = 0L;
      onChanged();
      return this;
    }

    private long delta_ ;
    /**
     * <code>uint64 delta = 4;</code>
     * @return The delta.
     */
    @Override
    public long getDelta() {
      return delta_;
    }
    /**
     * <code>uint64 delta = 4;</code>
     * @param value The delta to set.
     * @return This builder for chaining.
     */
    public Builder setDelta(long value) {

      delta_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 delta = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearDelta() {
      bitField0_ = (bitField0_ & ~0x00000008);
      delta_ = 0L;
      onChanged();
      return this;
    }

    private boolean isHidden_ ;
    /**
     * <code>bool isHidden = 5;</code>
     * @return The isHidden.
     */
    @Override
    public boolean getIsHidden() {
      return isHidden_;
    }
    /**
     * <code>bool isHidden = 5;</code>
     * @param value The isHidden to set.
     * @return This builder for chaining.
     */
    public Builder setIsHidden(boolean value) {

      isHidden_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>bool isHidden = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearIsHidden() {
      bitField0_ = (bitField0_ & ~0x00000010);
      isHidden_ = false;
      onChanged();
      return this;
    }

    private Object scoreDescription_ = "";
    /**
     * <code>string scoreDescription = 6;</code>
     * @return The scoreDescription.
     */
    public String getScoreDescription() {
      Object ref = scoreDescription_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        scoreDescription_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string scoreDescription = 6;</code>
     * @return The bytes for scoreDescription.
     */
    public com.google.protobuf.ByteString
        getScoreDescriptionBytes() {
      Object ref = scoreDescription_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        scoreDescription_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string scoreDescription = 6;</code>
     * @param value The scoreDescription to set.
     * @return This builder for chaining.
     */
    public Builder setScoreDescription(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      scoreDescription_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>string scoreDescription = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearScoreDescription() {
      scoreDescription_ = getDefaultInstance().getScoreDescription();
      bitField0_ = (bitField0_ & ~0x00000020);
      onChanged();
      return this;
    }
    /**
     * <code>string scoreDescription = 6;</code>
     * @param value The bytes for scoreDescription to set.
     * @return This builder for chaining.
     */
    public Builder setScoreDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      scoreDescription_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }

    private Object exactlyScore_ = "";
    /**
     * <code>string exactlyScore = 7;</code>
     * @return The exactlyScore.
     */
    public String getExactlyScore() {
      Object ref = exactlyScore_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        exactlyScore_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string exactlyScore = 7;</code>
     * @return The bytes for exactlyScore.
     */
    public com.google.protobuf.ByteString
        getExactlyScoreBytes() {
      Object ref = exactlyScore_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        exactlyScore_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string exactlyScore = 7;</code>
     * @param value The exactlyScore to set.
     * @return This builder for chaining.
     */
    public Builder setExactlyScore(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      exactlyScore_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>string exactlyScore = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearExactlyScore() {
      exactlyScore_ = getDefaultInstance().getExactlyScore();
      bitField0_ = (bitField0_ & ~0x00000040);
      onChanged();
      return this;
    }
    /**
     * <code>string exactlyScore = 7;</code>
     * @param value The bytes for exactlyScore to set.
     * @return This builder for chaining.
     */
    public Builder setExactlyScoreBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      exactlyScore_ = value;
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


    // @@protoc_insertion_point(builder_scope:RoomUserSeqMessageContributor)
  }

  // @@protoc_insertion_point(class_scope:RoomUserSeqMessageContributor)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RoomUserSeqMessageContributor>
      PARSER = new com.google.protobuf.AbstractParser<RoomUserSeqMessageContributor>() {
    @Override
    public RoomUserSeqMessageContributor parsePartialFrom(
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

  public static com.google.protobuf.Parser<RoomUserSeqMessageContributor> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<RoomUserSeqMessageContributor> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.RoomUserSeqMessageContributor getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

