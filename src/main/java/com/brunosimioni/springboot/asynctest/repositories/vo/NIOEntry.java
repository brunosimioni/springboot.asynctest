package com.brunosimioni.springboot.asynctest.repositories.vo;

import org.springframework.data.annotation.Id;

public class NIOEntry {

    @Id
    private String id;

    private String content;

    public NIOEntry() {}

    public NIOEntry(String content) {
        this.content= content;
    }

    @Override
    public String toString() {
        return String.format(
                "NIOEntry[id=%s, content='%s'']",
                id, content);
    }

}