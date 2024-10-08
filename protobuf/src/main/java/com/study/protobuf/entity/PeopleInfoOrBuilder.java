// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: contacts.proto
// Protobuf Java Version: 4.28.0

package com.study.protobuf.entity;

public interface PeopleInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.study.protobuf.entity.PeopleInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 age = 2;</code>
   * @return The age.
   */
  int getAge();

  /**
   * <pre>
   * 手机号，字符串List
   * </pre>
   *
   * <code>repeated .com.study.protobuf.entity.Phone phone_number = 3;</code>
   */
  java.util.List<com.study.protobuf.entity.Phone> 
      getPhoneNumberList();
  /**
   * <pre>
   * 手机号，字符串List
   * </pre>
   *
   * <code>repeated .com.study.protobuf.entity.Phone phone_number = 3;</code>
   */
  com.study.protobuf.entity.Phone getPhoneNumber(int index);
  /**
   * <pre>
   * 手机号，字符串List
   * </pre>
   *
   * <code>repeated .com.study.protobuf.entity.Phone phone_number = 3;</code>
   */
  int getPhoneNumberCount();
  /**
   * <pre>
   * 手机号，字符串List
   * </pre>
   *
   * <code>repeated .com.study.protobuf.entity.Phone phone_number = 3;</code>
   */
  java.util.List<? extends com.study.protobuf.entity.PhoneOrBuilder> 
      getPhoneNumberOrBuilderList();
  /**
   * <pre>
   * 手机号，字符串List
   * </pre>
   *
   * <code>repeated .com.study.protobuf.entity.Phone phone_number = 3;</code>
   */
  com.study.protobuf.entity.PhoneOrBuilder getPhoneNumberOrBuilder(
      int index);
}
