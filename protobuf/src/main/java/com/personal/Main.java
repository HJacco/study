package com.personal;

import com.study.protobuf.entity.PeopleInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        PeopleInfo peopleInfo = PeopleInfo.newBuilder().setAge(10).setName("Jacco").build();
        System.out.println(peopleInfo.toByteArray().length);

    }
}