package com.sunny.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sunny.memo.common.FileManager;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		// 어떤 url 경로로 접근하게 할건지 적어준다
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	}
}
