// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: douyin.proto

// Protobuf Java Version: 3.25.5
package org.caesar.crawler.live.douyin.codec.protobuf;

public interface TextFormatOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TextFormat)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string color = 1;</code>
   * @return The color.
   */
  String getColor();
  /**
   * <code>string color = 1;</code>
   * @return The bytes for color.
   */
  com.google.protobuf.ByteString
      getColorBytes();

  /**
   * <code>bool bold = 2;</code>
   * @return The bold.
   */
  boolean getBold();

  /**
   * <code>bool italic = 3;</code>
   * @return The italic.
   */
  boolean getItalic();

  /**
   * <code>uint32 weight = 4;</code>
   * @return The weight.
   */
  int getWeight();

  /**
   * <code>uint32 italic_angle = 5;</code>
   * @return The italicAngle.
   */
  int getItalicAngle();

  /**
   * <code>uint32 font_size = 6;</code>
   * @return The fontSize.
   */
  int getFontSize();

  /**
   * <code>bool use_heigh_light_color = 7;</code>
   * @return The useHeighLightColor.
   */
  boolean getUseHeighLightColor();

  /**
   * <code>bool use_remote_clor = 8;</code>
   * @return The useRemoteClor.
   */
  boolean getUseRemoteClor();
}
