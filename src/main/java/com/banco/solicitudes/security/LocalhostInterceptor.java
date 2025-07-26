package com.banco.solicitudes.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LocalhostInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ip = request.getRemoteAddr();

        boolean isLocal = "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip);

        if (request.getRequestURI().startsWith("/h2-console") && !isLocal) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: solo desde localhost");
            return false;
        }

        return true;
    }
}
