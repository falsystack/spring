package jp.falsystack.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/second-service/**")
                        .filters(filter -> filter.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/third-service/**")
                        .filters(filter -> filter.addRequestHeader("third-request", "third-request-header")
                                        .addResponseHeader("third-response", "third-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
