// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

/**
 * Protobuf type {@code NinePatchSetting}
 */
public final class NinePatchSetting extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:NinePatchSetting)
    NinePatchSettingOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NinePatchSetting.newBuilder() to construct.
  private NinePatchSetting(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NinePatchSetting() {
    settingListList_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new NinePatchSetting();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_NinePatchSetting_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_NinePatchSetting_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.class, org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.Builder.class);
  }

  public static final int SETTINGLISTLIST_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private com.google.protobuf.LazyStringArrayList settingListList_ =
      com.google.protobuf.LazyStringArrayList.emptyList();
  /**
   * <code>repeated string settingListList = 1;</code>
   * @return A list containing the settingListList.
   */
  public com.google.protobuf.ProtocolStringList
      getSettingListListList() {
    return settingListList_;
  }
  /**
   * <code>repeated string settingListList = 1;</code>
   * @return The count of settingListList.
   */
  public int getSettingListListCount() {
    return settingListList_.size();
  }
  /**
   * <code>repeated string settingListList = 1;</code>
   * @param index The index of the element to return.
   * @return The settingListList at the given index.
   */
  public String getSettingListList(int index) {
    return settingListList_.get(index);
  }
  /**
   * <code>repeated string settingListList = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the settingListList at the given index.
   */
  public com.google.protobuf.ByteString
      getSettingListListBytes(int index) {
    return settingListList_.getByteString(index);
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
    for (int i = 0; i < settingListList_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, settingListList_.getRaw(i));
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < settingListList_.size(); i++) {
        dataSize += computeStringSizeNoTag(settingListList_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getSettingListListList().size();
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
    if (!(obj instanceof org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting)) {
      return super.equals(obj);
    }
    org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting other = (org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting) obj;

    if (!getSettingListListList()
        .equals(other.getSettingListListList())) return false;
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
    if (getSettingListListCount() > 0) {
      hash = (37 * hash) + SETTINGLISTLIST_FIELD_NUMBER;
      hash = (53 * hash) + getSettingListListList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting parseFrom(
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
  public static Builder newBuilder(org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting prototype) {
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
   * Protobuf type {@code NinePatchSetting}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:NinePatchSetting)
      org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSettingOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_NinePatchSetting_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_NinePatchSetting_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.class, org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.Builder.class);
    }

    // Construct using org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.newBuilder()
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
      settingListList_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.Douyin.internal_static_NinePatchSetting_descriptor;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting getDefaultInstanceForType() {
      return org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.getDefaultInstance();
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting build() {
      org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting buildPartial() {
      org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting result = new org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        settingListList_.makeImmutable();
        result.settingListList_ = settingListList_;
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
      if (other instanceof org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting) {
        return mergeFrom((org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting other) {
      if (other == org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting.getDefaultInstance()) return this;
      if (!other.settingListList_.isEmpty()) {
        if (settingListList_.isEmpty()) {
          settingListList_ = other.settingListList_;
          bitField0_ |= 0x00000001;
        } else {
          ensureSettingListListIsMutable();
          settingListList_.addAll(other.settingListList_);
        }
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
              String s = input.readStringRequireUtf8();
              ensureSettingListListIsMutable();
              settingListList_.add(s);
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

    private com.google.protobuf.LazyStringArrayList settingListList_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    private void ensureSettingListListIsMutable() {
      if (!settingListList_.isModifiable()) {
        settingListList_ = new com.google.protobuf.LazyStringArrayList(settingListList_);
      }
      bitField0_ |= 0x00000001;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @return A list containing the settingListList.
     */
    public com.google.protobuf.ProtocolStringList
        getSettingListListList() {
      settingListList_.makeImmutable();
      return settingListList_;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @return The count of settingListList.
     */
    public int getSettingListListCount() {
      return settingListList_.size();
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param index The index of the element to return.
     * @return The settingListList at the given index.
     */
    public String getSettingListList(int index) {
      return settingListList_.get(index);
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the settingListList at the given index.
     */
    public com.google.protobuf.ByteString
        getSettingListListBytes(int index) {
      return settingListList_.getByteString(index);
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param index The index to set the value at.
     * @param value The settingListList to set.
     * @return This builder for chaining.
     */
    public Builder setSettingListList(
        int index, String value) {
      if (value == null) { throw new NullPointerException(); }
      ensureSettingListListIsMutable();
      settingListList_.set(index, value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param value The settingListList to add.
     * @return This builder for chaining.
     */
    public Builder addSettingListList(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      ensureSettingListListIsMutable();
      settingListList_.add(value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param values The settingListList to add.
     * @return This builder for chaining.
     */
    public Builder addAllSettingListList(
        Iterable<String> values) {
      ensureSettingListListIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, settingListList_);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSettingListList() {
      settingListList_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);;
      onChanged();
      return this;
    }
    /**
     * <code>repeated string settingListList = 1;</code>
     * @param value The bytes of the settingListList to add.
     * @return This builder for chaining.
     */
    public Builder addSettingListListBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      ensureSettingListListIsMutable();
      settingListList_.add(value);
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


    // @@protoc_insertion_point(builder_scope:NinePatchSetting)
  }

  // @@protoc_insertion_point(class_scope:NinePatchSetting)
  private static final org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting();
  }

  public static org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NinePatchSetting>
      PARSER = new com.google.protobuf.AbstractParser<NinePatchSetting>() {
    @Override
    public NinePatchSetting parsePartialFrom(
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

  public static com.google.protobuf.Parser<NinePatchSetting> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<NinePatchSetting> getParserForType() {
    return PARSER;
  }

  @Override
  public org.caesar.crawler.live.douyin.codec.protobuf.NinePatchSetting getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

