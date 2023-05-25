package com.example.dto;

import java.util.List;

import com.example.entity.Printing;

import lombok.Data;

@Data
public class PrintingDTO {
    
    List<Printing> list;

    private String selectpmethod;
}
