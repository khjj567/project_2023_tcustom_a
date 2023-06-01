package com.example.dto;

import java.math.BigInteger;
import java.util.List;

import com.example.entity.TshirtPrintingSidePicView;

import lombok.Data;

@Data
public class TshirtPrintingSidePicViewDTO {
    List<TshirtPrintingSidePicView> list;

    private long selectpside;
}
