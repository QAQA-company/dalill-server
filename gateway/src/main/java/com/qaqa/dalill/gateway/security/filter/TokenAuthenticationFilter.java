package com.qaqa.dalill.gateway.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TokenAuthenticationFilter implements GatewayFilterFactory<TokenAuthenticationFilter.Config> {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            if (path.startsWith("/user")) {
                return chain.filter(exchange); // /user 경로는 인증 없이 허용
            }

            String token = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(SECRET_KEY)
                            .parseClaimsJws(token)
                            .getBody();
                } catch (Exception e) {
                    return Mono.error(new RuntimeException("Invalid JWT token"));
                }
            } else {
                return Mono.error(new RuntimeException("Missing JWT token"));
            }

            return chain.filter(exchange);
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }
}
