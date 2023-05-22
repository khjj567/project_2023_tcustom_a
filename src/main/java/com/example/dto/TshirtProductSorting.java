package com.example.dto;

import java.math.BigInteger;

import lombok.Data;

@Data
public class TshirtProductSorting {
        // 검색할때 콤보박스에 들어가는 값들인가?
        final int[] typeCode = {1,2,3}; // value
        final String[] typeName = {"티셔츠","후드 & 맨투맨","후드집업 & 아우터"}; // text
        
        // typeCode는 entity의 속성값 name이고 
        // typeName는 우리가 화면에 콤보박스에서 보여주고 싶은 내용이다
        // 이 두가지는 index순서로 1대1로 대응시킨다(html에서)
        
        private int page=1; //?
        private int type=1;
        private String text="";
}
