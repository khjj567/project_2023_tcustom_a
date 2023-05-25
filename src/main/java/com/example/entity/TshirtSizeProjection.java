package com.example.entity;

import java.math.BigInteger;
// 프로젝션 만들지 말고 뷰 만들기(getTssize getTno)
public interface TshirtSizeProjection {
    // 타입 get+대문자변수..();
    BigInteger getTsno();

    String getTssize();

    Tshirt getTshirt();

    interface Tshirt { // 외래키 항목
        BigInteger getTno();
    }
    
    // String getMemail();
}
