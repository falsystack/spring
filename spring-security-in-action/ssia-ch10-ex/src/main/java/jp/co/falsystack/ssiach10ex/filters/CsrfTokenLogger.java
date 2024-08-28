package jp.co.falsystack.ssiach10ex.filters;

import jakarta.servlet.*;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;
import java.util.logging.Logger;

public class CsrfTokenLogger implements Filter {

    private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var o = request.getAttribute("_csrf");
        var token = (CsrfToken) o;

        logger.info("CSRF token: " + token.getToken());
        filterChain.doFilter(request, response);
    }
}
