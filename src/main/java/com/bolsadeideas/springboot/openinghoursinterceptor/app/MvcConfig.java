package com.bolsadeideas.springboot.openinghoursinterceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	// sobreescribimos el método de agregar interceptores, primero tenemos que
	// inyectar el interceptor con su tipo genérico
	@Autowired
	@Qualifier("openingHoursInterceptor")
	private HandlerInterceptor openingHoursInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//
		registry.addInterceptor(openingHoursInterceptor).excludePathPatterns("/closed");
	}

}
