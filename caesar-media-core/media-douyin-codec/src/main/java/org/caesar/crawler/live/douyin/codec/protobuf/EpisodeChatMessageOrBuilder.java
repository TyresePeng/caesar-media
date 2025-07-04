// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

public interface EpisodeChatMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:EpisodeChatMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Message common = 1;</code>
   * @return Whether the common field is set.
   */
  boolean hasCommon();
  /**
   * <code>.Message common = 1;</code>
   * @return The common.
   */
  org.caesar.crawler.live.douyin.codec.protobuf.Message getCommon();
  /**
   * <code>.Message common = 1;</code>
   */
  org.caesar.crawler.live.douyin.codec.protobuf.MessageOrBuilder getCommonOrBuilder();

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
   * <code>string content = 3;</code>
   * @return The content.
   */
  String getContent();
  /**
   * <code>string content = 3;</code>
   * @return The bytes for content.
   */
  com.google.protobuf.ByteString
      getContentBytes();

  /**
   * <code>bool visibleToSende = 4;</code>
   * @return The visibleToSende.
   */
  boolean getVisibleToSende();

  /**
   * <pre>
   *   BackgroundImage backgroundImage = 5;
   *   PublicAreaCommon publicAreaCommon = 6;
   * </pre>
   *
   * <code>.Image giftImage = 7;</code>
   * @return Whether the giftImage field is set.
   */
  boolean hasGiftImage();
  /**
   * <pre>
   *   BackgroundImage backgroundImage = 5;
   *   PublicAreaCommon publicAreaCommon = 6;
   * </pre>
   *
   * <code>.Image giftImage = 7;</code>
   * @return The giftImage.
   */
  org.caesar.crawler.live.douyin.codec.protobuf.Image getGiftImage();
  /**
   * <pre>
   *   BackgroundImage backgroundImage = 5;
   *   PublicAreaCommon publicAreaCommon = 6;
   * </pre>
   *
   * <code>.Image giftImage = 7;</code>
   */
  org.caesar.crawler.live.douyin.codec.protobuf.ImageOrBuilder getGiftImageOrBuilder();

  /**
   * <code>uint64 agreeMsgId = 8;</code>
   * @return The agreeMsgId.
   */
  long getAgreeMsgId();

  /**
   * <code>repeated string colorValueList = 9;</code>
   * @return A list containing the colorValueList.
   */
  java.util.List<String>
      getColorValueListList();
  /**
   * <code>repeated string colorValueList = 9;</code>
   * @return The count of colorValueList.
   */
  int getColorValueListCount();
  /**
   * <code>repeated string colorValueList = 9;</code>
   * @param index The index of the element to return.
   * @return The colorValueList at the given index.
   */
  String getColorValueList(int index);
  /**
   * <code>repeated string colorValueList = 9;</code>
   * @param index The index of the value to return.
   * @return The bytes of the colorValueList at the given index.
   */
  com.google.protobuf.ByteString
      getColorValueListBytes(int index);
}
