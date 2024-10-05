package jp.co.falsystack.apigatewayservice.confg;

import jp.co.falsystack.apigatewayservice.filter.CustomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {

    private final CustomFilter customFilter;

    @Bean
    protected RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()

                .route(r -> r
                        .path("/first-service/**")
                        .filters(f -> f
                                .filter(customFilter)
                                .addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-response", "first-response-header")
                        )
                        .uri("http://localhost:8081")
                )
                .route(r -> r
                        .path("/second-service/**")
                        .filters(f -> f
                                .filter(customFilter)
                                .addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header")
                        )
                        .uri("http://localhost:8082")
                )
                .build();
    }

}
