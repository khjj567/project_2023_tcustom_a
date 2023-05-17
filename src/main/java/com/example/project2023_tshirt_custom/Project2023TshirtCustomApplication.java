package com.example.project2023_tshirt_custom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource(value = {"classpath:global.properties"}) //직접만든 환경파일 위치
@MapperScan(basePackages = {"com.example.mapper"}) // 매퍼위치
@ComponentScan(basePackages = {
	"com.example.controller", 
	"com.example.controller.jpa", 
	"com.example.controller.mybatis", 
	"com.example.service", 
	"com.example.config",
	"com.example.restcontroller",
	"com.example.filter"
	})
	@EntityScan(basePackages = {"com.example.entity"}) // 엔티티 위치
	@EnableJpaRepositories(basePackages = {"com.example.repository"}) // 저장소 위치

public class Project2023TshirtCustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2023TshirtCustomApplication.class, args);
	}

}
