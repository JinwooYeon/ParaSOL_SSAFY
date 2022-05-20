package com.parasol.main_authentication.security.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    private static final Logger logger = LogManager.getLogger(GlobalFilter.class);
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(GlobalFilter.Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String uri = config.getUri();

            if (config.isPreLogger()) {
                log.info("GlobalFilter Start >>>>>>");
            }

            InetSocketAddress ipSocketAddr = request.getRemoteAddress();
            if (ipSocketAddr == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            String ipAddr = ipSocketAddr.getAddress().toString();
            log.info("GlobalFilter :: [from: " + uri + " - " + ipAddr + "]");

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(()->{
                        if (config.isPostLogger()) {
                            log.info("<<<<<< GlobalFilter End");
                        }
                    }));
        });
    }

    @Data
    public static class Config {
        private String baseMessage;
        private String uri;
        private boolean preLogger;
        private boolean postLogger;
    }
}
