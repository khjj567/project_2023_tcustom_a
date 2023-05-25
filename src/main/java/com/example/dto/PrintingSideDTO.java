package com.example.dto;

import java.util.List;

import com.example.entity.PrintingSide;

import lombok.Data;

@Data
public class PrintingSideDTO {
    
    List<PrintingSide> list;

    private String selectpside;
}
