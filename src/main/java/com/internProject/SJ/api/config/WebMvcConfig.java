package com.internProject.SJ.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private LogInterceptor logInterceptor;
	private RequestResponseWrappingFilter requestResponseWrappingFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/api/**"); // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
    }
}
