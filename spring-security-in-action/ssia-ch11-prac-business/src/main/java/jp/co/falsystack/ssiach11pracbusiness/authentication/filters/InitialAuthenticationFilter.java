package jp.co.falsystack.ssiach11pracbusiness.authentication.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.falsystack.ssiach11pracbusiness.authentication.OtpAuthentication;
import jp.co.falsystack.ssiach11pracbusiness.authentication.UsernamePasswordAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
//@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var code = request.getHeader("code");

        if (code == null) {
            var authentication = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(authentication);
        } else {
            var authentication = new OtpAuthentication(username, password);
            manager.authenticate(authentication);

            var key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            var jwt = Jwts.builder()
                    .claims(Map.of("username", username))
                    .signWith(key)
                    .compact();

            response.setHeader("Authorization", jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
