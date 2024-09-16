package com.personal;

import com.study.protobuf.entity.PeopleInfo;
import com.study.protobuf.entity.Phone;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        PeopleInfo peopleInfo = PeopleInfo.newBuilder()
                .setAge(10)
                .setName("Jacco")
                .addAllPhoneNumber(new ArrayList<>())
                .build();
        System.out.println(peopleInfo.toByteArray().length);

    }
}