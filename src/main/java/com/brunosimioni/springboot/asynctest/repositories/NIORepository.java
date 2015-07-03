package com.brunosimioni.springboot.asynctest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.brunosimioni.springboot.asynctest.repositories.vo.NIOEntry;

public interface NIORepository extends MongoRepository<NIOEntry, String> {

    public NIOEntry findByContent(String content);
}