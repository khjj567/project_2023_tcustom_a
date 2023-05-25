package com.example.dto;

import java.util.List;
import com.example.entity.TshirtSize;
import lombok.Data;

@Data
public class TshirtSizeDTO {

    List<TshirtSize> list; 
    private String selectsize="";

}
