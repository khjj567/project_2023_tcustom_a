package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
    
    private String mid;
    private String mname;
    private String mpw;
    private String mphone;
    private String memail;
    private Date mregdate;
}
