package com.example.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 이미 있는 User에는 name이 없다.
// 로그인 여부에 따라 홈화면에 보여줄 정보를 고르고 싶다면(뫄뫄님 환영합니다!)
// User을 우리상황에 맞게 만들어야 한다.
// 아래와 같이 임의로 name을 추가해서 MemberUser를 생성한다.

@Getter
@Setter
@ToString (exclude = {"password"})
public class MemberUser extends User {

    //임의로 생성
    // private String 임의로 생성한 이름(들);
    private String username;    //username
	private String password; //password
	private Collection<GrantedAuthority> authorities; //role
	private String name;
    
	// User의 생성자 기본구조
	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	// 기본구조에서 추가할 내용포함(이름)
	public MemberUser(String username, String password, Collection<GrantedAuthority> authorities, String name) {
		super(username, password, authorities);
		this.authorities = authorities;
		this.username = username;
		this.password = password;
		this.name = name;

	}
    
}
