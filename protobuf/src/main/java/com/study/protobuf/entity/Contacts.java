// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: contacts.proto
// Protobuf Java Version: 4.28.0

package com.study.protobuf.entity;

public final class Contacts {
  private Contacts() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 28,
      /* patch= */ 0,
      /* suffix= */ "",
      Contacts.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_study_protobuf_entity_PeopleInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_study_protobuf_entity_PeopleInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016contacts.proto\022\031com.study.protobuf.ent" +
      "ity\032\013phone.proto\"_\n\nPeopleInfo\022\014\n\004name\030\001" +
      " \001(\t\022\013\n\003age\030\002 \001(\005\0226\n\014phone_number\030\003 \003(\0132" +
      " .com.study.protobuf.entity.PhoneB\035\n\031com" +
      ".study.protobuf.entityP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.study.protobuf.entity.PhoneOuterClass.getDescriptor(),
        });
    internal_static_com_study_protobuf_entity_PeopleInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_study_protobuf_entity_PeopleInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_study_protobuf_entity_PeopleInfo_descriptor,
        new java.lang.String[] { "Name", "Age", "PhoneNumber", });
    descriptor.resolveAllFeaturesImmutable();
    com.study.protobuf.entity.PhoneOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
