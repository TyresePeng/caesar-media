// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code ProductInfo}
 */
public final class ProductInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ProductInfo)
    ProductInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ProductInfo.newBuilder() to construct.
  private ProductInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProductInfo() {
    targetFlashUidsList_ = emptyLongList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new ProductInfo();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_ProductInfo_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_ProductInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.class, org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.Builder.class);
  }

  public static final int PROMOTIONID_FIELD_NUMBER = 1;
  private long promotionId_ = 0L;
  /**
   * <code>int64 promotionId = 1;</code>
   * @return The promotionId.
   */
  @Override
  public long getPromotionId() {
    return promotionId_;
  }

  public static final int INDEX_FIELD_NUMBER = 2;
  private int index_ = 0;
  /**
   * <code>int32 index = 2;</code>
   * @return The index.
   */
  @Override
  public int getIndex() {
    return index_;
  }

  public static final int TARGETFLASHUIDSLIST_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.LongList targetFlashUidsList_ =
      emptyLongList();
  /**
   * <code>repeated int64 targetFlashUidsList = 3;</code>
   * @return A list containing the targetFlashUidsList.
   */
  @Override
  public java.util.List<Long>
      getTargetFlashUidsListList() {
    return targetFlashUidsList_;
  }
  /**
   * <code>repeated int64 targetFlashUidsList = 3;</code>
   * @return The count of targetFlashUidsList.
   */
  public int getTargetFlashUidsListCount() {
    return targetFlashUidsList_.size();
  }
  /**
   * <code>repeated int64 targetFlashUidsList = 3;</code>
   * @param index The index of the element to return.
   * @return The targetFlashUidsList at the given index.
   */
  public long getTargetFlashUidsList(int index) {
    return targetFlashUidsList_.getLong(index);
  }
  private int targetFlashUidsListMemoizedSerializedSize = -1;

  public static final int EXPLAINTYPE_FIELD_NUMBER = 4;
  private long explainType_ = 0L;
  /**
   * <code>int64 explainType = 4;</code>
   * @return The explainType.
   */
  @Override
  public long getExplainType() {
    return explainType_;
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
    getSerializedSize();
    if (promotionId_ != 0L) {
      output.writeInt64(1, promotionId_);
    }
    if (index_ != 0) {
      output.writeInt32(2, index_);
    }
    if (getTargetFlashUidsListList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(targetFlashUidsListMemoizedSerializedSize);
    }
    for (int i = 0; i < targetFlashUidsList_.size(); i++) {
      output.writeInt64NoTag(targetFlashUidsList_.getLong(i));
    }
    if (explainType_ != 0L) {
      output.writeInt64(4, explainType_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (promotionId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, promotionId_);
    }
    if (index_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, index_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < targetFlashUidsList_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(targetFlashUidsList_.getLong(i));
      }
      size += dataSize;
      if (!getTargetFlashUidsListList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      targetFlashUidsListMemoizedSerializedSize = dataSize;
    }
    if (explainType_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, explainType_);
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
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo other = (org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo) obj;

    if (getPromotionId()
        != other.getPromotionId()) return false;
    if (getIndex()
        != other.getIndex()) return false;
    if (!getTargetFlashUidsListList()
        .equals(other.getTargetFlashUidsListList())) return false;
    if (getExplainType()
        != other.getExplainType()) return false;
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
    hash = (37 * hash) + PROMOTIONID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPromotionId());
    hash = (37 * hash) + INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getIndex();
    if (getTargetFlashUidsListCount() > 0) {
      hash = (37 * hash) + TARGETFLASHUIDSLIST_FIELD_NUMBER;
      hash = (53 * hash) + getTargetFlashUidsListList().hashCode();
    }
    hash = (37 * hash) + EXPLAINTYPE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getExplainType());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo parseFrom(
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
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo prototype) {
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
   * Protobuf type {@code ProductInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ProductInfo)
      org.caesar.crawler.live.douyin.codec.protobuf.ProductInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_ProductInfo_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_ProductInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.class, org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.newBuilder()
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
      promotionId_ = 0L;
      index_ = 0;
      targetFlashUidsList_ = emptyLongList();
      explainType_ = 0L;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_ProductInfo_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo build() {
      org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo result = new org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.promotionId_ = promotionId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.index_ = index_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        targetFlashUidsList_.makeImmutable();
        result.targetFlashUidsList_ = targetFlashUidsList_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.explainType_ = explainType_;
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
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo.getDefaultInstance()) return this;
      if (other.getPromotionId() != 0L) {
        setPromotionId(other.getPromotionId());
      }
      if (other.getIndex() != 0) {
        setIndex(other.getIndex());
      }
      if (!other.targetFlashUidsList_.isEmpty()) {
        if (targetFlashUidsList_.isEmpty()) {
          targetFlashUidsList_ = other.targetFlashUidsList_;
          targetFlashUidsList_.makeImmutable();
          bitField0_ |= 0x00000004;
        } else {
          ensureTargetFlashUidsListIsMutable();
          targetFlashUidsList_.addAll(other.targetFlashUidsList_);
        }
        onChanged();
      }
      if (other.getExplainType() != 0L) {
        setExplainType(other.getExplainType());
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
              promotionId_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              index_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              long v = input.readInt64();
              ensureTargetFlashUidsListIsMutable();
              targetFlashUidsList_.addLong(v);
              break;
            } // case 24
            case 26: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureTargetFlashUidsListIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                targetFlashUidsList_.addLong(input.readInt64());
              }
              input.popLimit(limit);
              break;
            } // case 26
            case 32: {
              explainType_ = input.readInt64();
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

    private long promotionId_ ;
    /**
     * <code>int64 promotionId = 1;</code>
     * @return The promotionId.
     */
    @Override
    public long getPromotionId() {
      return promotionId_;
    }
    /**
     * <code>int64 promotionId = 1;</code>
     * @param value The promotionId to set.
     * @return This builder for chaining.
     */
    public Builder setPromotionId(long value) {

      promotionId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 promotionId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPromotionId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      promotionId_ = 0L;
      onChanged();
      return this;
    }

    private int index_ ;
    /**
     * <code>int32 index = 2;</code>
     * @return The index.
     */
    @Override
    public int getIndex() {
      return index_;
    }
    /**
     * <code>int32 index = 2;</code>
     * @param value The index to set.
     * @return This builder for chaining.
     */
    public Builder setIndex(int value) {

      index_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>int32 index = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearIndex() {
      bitField0_ = (bitField0_ & ~0x00000002);
      index_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Internal.LongList targetFlashUidsList_ = emptyLongList();
    private void ensureTargetFlashUidsListIsMutable() {
      if (!targetFlashUidsList_.isModifiable()) {
        targetFlashUidsList_ = makeMutableCopy(targetFlashUidsList_);
      }
      bitField0_ |= 0x00000004;
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @return A list containing the targetFlashUidsList.
     */
    public java.util.List<Long>
        getTargetFlashUidsListList() {
      targetFlashUidsList_.makeImmutable();
      return targetFlashUidsList_;
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @return The count of targetFlashUidsList.
     */
    public int getTargetFlashUidsListCount() {
      return targetFlashUidsList_.size();
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @param index The index of the element to return.
     * @return The targetFlashUidsList at the given index.
     */
    public long getTargetFlashUidsList(int index) {
      return targetFlashUidsList_.getLong(index);
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @param index The index to set the value at.
     * @param value The targetFlashUidsList to set.
     * @return This builder for chaining.
     */
    public Builder setTargetFlashUidsList(
        int index, long value) {

      ensureTargetFlashUidsListIsMutable();
      targetFlashUidsList_.setLong(index, value);
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @param value The targetFlashUidsList to add.
     * @return This builder for chaining.
     */
    public Builder addTargetFlashUidsList(long value) {

      ensureTargetFlashUidsListIsMutable();
      targetFlashUidsList_.addLong(value);
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @param values The targetFlashUidsList to add.
     * @return This builder for chaining.
     */
    public Builder addAllTargetFlashUidsList(
        Iterable<? extends Long> values) {
      ensureTargetFlashUidsListIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, targetFlashUidsList_);
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 targetFlashUidsList = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetFlashUidsList() {
      targetFlashUidsList_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }

    private long explainType_ ;
    /**
     * <code>int64 explainType = 4;</code>
     * @return The explainType.
     */
    @Override
    public long getExplainType() {
      return explainType_;
    }
    /**
     * <code>int64 explainType = 4;</code>
     * @param value The explainType to set.
     * @return This builder for chaining.
     */
    public Builder setExplainType(long value) {

      explainType_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int64 explainType = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearExplainType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      explainType_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:ProductInfo)
  }

  // @@protoc_insertion_point(class_scope:ProductInfo)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProductInfo>
      PARSER = new com.google.protobuf.AbstractParser<ProductInfo>() {
    @Override
    public ProductInfo parsePartialFrom(
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

  public static com.google.protobuf.Parser<ProductInfo> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ProductInfo> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.ProductInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

