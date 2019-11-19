package com.wrf.backend.config;

import com.wrf.backend.HeaderConst;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.exception.RestException;
import com.wrf.backend.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingRequestWrapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class TokenAuthenticationFilter implements Filter {

    private static final List<String> FREE_SERVLET_PATHS = Arrays.asList("/users", "/auth/login", "/", "/ping", "/monitoring");

    private static final Logger LOG = LogManager.getLogger(TokenAuthenticationFilter.class);

    private final AuthService authService;

    TokenAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) req);
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();
        if (FREE_SERVLET_PATHS.contains(path)) {
            chain.doFilter(request, response);
            return;
        }
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HeaderConst.ACCESS_CONTROL_ALLOW_ORIGIN);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HeaderConst.ACCESS_CONTROL_ALLOW_HEADERS);
        response.setContentType(HeaderConst.CONTENT_TYPE);
        String token = getTokenFromHeader(request);
        try {
            authService.checkAccessToken(token);
        } catch (RestException e) {
            LOG.error(e);
            response.getWriter().print(new Response(e.getCode(), e.getMessage()));
            return;
        }
        chain.doFilter(wrappedRequest, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
       return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}