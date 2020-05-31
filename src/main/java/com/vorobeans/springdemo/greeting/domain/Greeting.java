package com.vorobeans.springdemo.greeting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Greeting {

    @Getter
    private final String content;

}
