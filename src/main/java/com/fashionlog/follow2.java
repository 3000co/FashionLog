package com.fashionlog;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FOLLOW")
public class follow2 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer FOLLOW_NO;
    private Integer FOLLOWER_MEM_NO;
    private Integer FOLLOWEE_MEM_NO;
    private java.sql.Timestamp FOLLOW_TIME;
    public Integer getFOLLOW_NO() {
        return this.FOLLOW_NO;
    }


    public follow2() {

    }

    public Integer getFOLLOWER_MEM_NO() {
        return this.FOLLOWER_MEM_NO;
    }
    
    public void setFOLLOWER_MEM_NO(Integer FOLLOWER_MEM_NO) {
        this.FOLLOWER_MEM_NO = FOLLOWER_MEM_NO;
    }
        
    public Integer getFOLLOWEE_MEM_NO() {
        return this.FOLLOWEE_MEM_NO;
    }
    
    public void setFOLLOWEE_MEM_NO(Integer FOLLOWEE_MEM_NO) {
        this.FOLLOWEE_MEM_NO = FOLLOWEE_MEM_NO;
    }
    
    public Timestamp getFOLLOW_TIME() {
        return this.FOLLOW_TIME;
    }

    public void setFOLLOW_TIME(Timestamp FOLLOW_TIME) {
        this.FOLLOW_TIME = FOLLOW_TIME;
    }

}