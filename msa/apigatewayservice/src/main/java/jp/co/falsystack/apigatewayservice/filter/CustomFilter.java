package jp.co.falsystack.apigatewayservice.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();
        var response = exchange.getResponse();
        log.info("Custom PRE filter: request id -> {}", request.getId());


        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Custom POST filter: response code -> {}", response.getStatusCode());
        }));
    }

    public static class Config {
        // Put the configuration properties
    }
}
