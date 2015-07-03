package com.brunosimioni.springboot.asynctest.resources.vo;

public class NIOResponse {
    
	private long id;
	private int expensiveTime;

    public NIOResponse(long id, int expensiveTime) {
        this.id = id;
        this.expensiveTime = expensiveTime;
    }

    public long getId() {
        return id;
    }

    public int getExpensiveTime() {
        return expensiveTime;
    }
}