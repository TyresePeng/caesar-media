// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

public interface CommonTextMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CommonTextMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Common common = 1;</code>
   * @return Whether the common field is set.
   */
  boolean hasCommon();
  /**
   * <code>.Common common = 1;</code>
   * @return The common.
   */
  org.caesar.crawler.live.douyin.codec.protobuf.Common getCommon();
  /**
   * <code>.Common common = 1;</code>
   */
  org.caesar.crawler.live.douyin.codec.protobuf.CommonOrBuilder getCommonOrBuilder();

  /**
   * <code>.User user = 2;</code>
   * @return Whether the user field is set.
   */
  boolean hasUser();
  /**
   * <code>.User user = 2;</code>
   * @return The user.
   */
  org.caesar.crawler.live.douyin.codec.protobuf.User getUser();
  /**
   * <code>.User user = 2;</code>
   */
  org.caesar.crawler.live.douyin.codec.protobuf.UserOrBuilder getUserOrBuilder();

  /**
   * <code>string scene = 3;</code>
   * @return The scene.
   */
  String getScene();
  /**
   * <code>string scene = 3;</code>
   * @return The bytes for scene.
   */
  com.google.protobuf.ByteString
      getSceneBytes();
}
