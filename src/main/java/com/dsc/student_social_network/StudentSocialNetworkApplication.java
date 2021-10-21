package com.dsc.student_social_network;

import com.dsc.student_social_network.filter.JWTFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentSocialNetworkApplication {

    @Bean
    public FilterRegistrationBean<JWTFilter> filterJwt() {
        FilterRegistrationBean<JWTFilter> filtroJwt = new FilterRegistrationBean<>();
        filtroJwt.setFilter(new JWTFilter());
        filtroJwt.addUrlPatterns("/courses/likes/*", "/courses/{id}/*", "/disciplinas/comment/*", "/api/login",
                "/users/{id}");
        return filtroJwt;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentSocialNetworkApplication.class, args);
    }

}
