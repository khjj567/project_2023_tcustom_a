package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.Member;

@Mapper
public interface MemberMapper {
 
    // 아이디를 전달하면 모두 받아올 수 있게
    public Member selectMemberONElogin(String id);
}
