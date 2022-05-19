package com.parasol.main_authentication.security.filter;

import com.parasol.main_authentication.service.AccountAuthenticateService;
import com.parasol.main_authentication.service.AuthenticateService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Slf4j
@Component
public class AccountFilter extends AbstractGatewayFilterFactory<AccountFilter.Config> {
    @Autowired
    private AccountAuthenticateService service;

    private static final Logger logger = LogManager.getLogger(AccountFilter.class);
    public AccountFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AccountFilter.Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String uri = config.getUri();

            if (config.isPreLogger()) {
                log.info("AccountFilter Start >>>>>>");
            }

            InetSocketAddress ipSocketAddr = request.getRemoteAddress();
            if (ipSocketAddr == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            String ipAddr = ipSocketAddr.getAddress().toString();

            if (!containsAuthorization(request)) {
                log.error("Unauthorized :: Authorization Header is empty [from: " + uri + " - " + ipAddr + "]");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            String token = extractToken(request);

            if (token.isEmpty()) {
                log.error("Unauthorized :: Authorization Header is empty [from: " + uri + " - " + ipAddr + "]");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            String method = request.getMethodValue();

            if (!hasPermission(ipAddr, method, uri, token)) {
                log.error("Forbidden :: [" + uri + " - " + ipAddr + " - " + token + "] has not permission");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(()->{
                        if (config.isPostLogger()) {
                            log.info("<<<<<< AccountFilter End");
                        }
                    }));
        });
    }

    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0).split(" ")[1];
    }

    private boolean hasPermission(String ipAddr, String method, String uri, String token) {
        return service.isValid(ipAddr, method, uri, token);
    }

    @Data
    public static class Config {
        private String baseMessage;
        private String uri;
        private boolean preLogger;
        private boolean postLogger;
    }
}
