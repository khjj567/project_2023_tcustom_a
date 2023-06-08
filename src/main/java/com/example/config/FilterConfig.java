package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.JwtFilter;
import com.example.filter.UrlFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FilterConfig {
    @Bean //서버가 구동될때 자동으로 호출됨
    // JwtFilter필터가 적용되는 url을 설정
    public FilterRegistrationBean<JwtFilter> filterRegistrationBean(JwtFilter jwtFilter){
        log.info("filter1111 => {}" , "filterConfig1111");
        FilterRegistrationBean<JwtFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(jwtFilter);

        // 필터처리할 url 두개 걸어놈
        filterReg.addUrlPatterns("/member/update.json");
        filterReg.addUrlPatterns("/member/delete.json");
        // filterReg.addUrlPatterns("/api/*");
        return filterReg;
    }

    @Bean //서버가 구동될때 자동으로 호출됨
    public FilterRegistrationBean<UrlFilter> filterRegistrationBean1(UrlFilter urlFilter){
        log.info("filter => {}" , "filterConfig");
        FilterRegistrationBean<UrlFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(urlFilter);
        filterReg.addUrlPatterns("/product/making.do");   // *는 전체 url
        // filterReg.addUrlPatterns("/product/*");   // *는 전체 url

        return filterReg;
    }
}
