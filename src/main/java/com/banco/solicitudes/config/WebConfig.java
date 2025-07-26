package com.banco.solicitudes.config;

import com.banco.solicitudes.security.LocalhostInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LocalhostInterceptor localhostInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localhostInterceptor)
                .addPathPatterns("/h2-console/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Opcional: redireccionar /h2 a /h2-console
        registry.addRedirectViewController("/h2", "/h2-console");
    }
}
