package co.enyoi.apigateway.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RequestFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Applying RequestFilter - X-Request-ID");

        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-Request-ID", UUID.randomUUID().toString())
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }
    
}
