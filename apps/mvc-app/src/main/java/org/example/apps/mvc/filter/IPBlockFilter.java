package org.example.apps.mvc.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rival
 * @since 2023-12-10
 */
@Component
public class IPBlockFilter implements Filter {

    private static final Set<String> blockedIPs = new HashSet<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String clientIP = request.getRemoteAddr();


        if ("/block".equals(httpServletRequest.getRequestURI()) && "GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
            blockedIPs.add(clientIP);
        }

        if (blockedIPs.contains(clientIP)) {
            response.getWriter().write("Blocked IP");
            return;
        }

        chain.doFilter(request, response);
    }
}
