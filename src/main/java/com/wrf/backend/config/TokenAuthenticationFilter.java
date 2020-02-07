package com.wrf.backend.config;

import com.wrf.backend.HeaderConst;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static com.wrf.backend.exception.ErrorMessage.*;

public final class TokenAuthenticationFilter implements Filter {

    private static final List<String> FREE_SERVLET_PATHS = Arrays.asList("/users", "/auth/login",
            "/ping", "/monitoring", "/webjars/springfox-swagger", "/swagger", "/v2/api-docs");

    private static final Logger LOG = LogManager.getLogger(TokenAuthenticationFilter.class);

    private final AuthService authService;

    TokenAuthenticationFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        var response = (HttpServletResponse) resp;
        var wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) req);
        var request = (HttpServletRequest) req;
        var path = request.getServletPath();
        long count = FREE_SERVLET_PATHS.stream()
                .filter(freePath -> StringUtils.startsWith(path, freePath))
                .count();
        // Если на url можно попасть без регистрации, то пускаем сразу
        if (count > 0 || path.equals("/")) {
            chain.doFilter(request, response);
            return;
        }

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, HeaderConst.ACCESS_CONTROL_ALLOW_ORIGIN);
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HeaderConst.ACCESS_CONTROL_ALLOW_HEADERS);
        response.setContentType(HeaderConst.CONTENT_TYPE);

        try {
            var token = Optional.ofNullable(getTokenFromHeader(request))
                    .orElseThrow(UnauthorizedException::new);
            authService.checkAccessToken(token);
        } catch (UnauthorizedException e) {
            LOG.error(e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().print(new Response(NO_AUTHORIZATION));
            return;
        }
        chain.doFilter(wrappedRequest, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
       return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
