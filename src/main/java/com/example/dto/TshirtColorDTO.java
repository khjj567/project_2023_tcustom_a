package com.example.dto;

import java.util.List;

import com.example.entity.TshirtColor;

import lombok.Data;

@Data
public class TshirtColorDTO {
    
    List<TshirtColor> list;
    
    private String selectcolor;
}
