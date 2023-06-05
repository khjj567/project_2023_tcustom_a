package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MID")
    private String mid;

    private String mname;
    private String mpw;
    private String mphone;
    private String memail;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp // 변경 시 날짜 정보 변경
    private Date mregdate;

    @ToString.Exclude
    @OneToMany(mappedBy = "member", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<DesignOne> list = new ArrayList<>();
}
