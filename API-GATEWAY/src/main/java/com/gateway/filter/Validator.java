package com.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {

    public static final List<String> endpoints = List.of(
            "/register-user",
            "/generate-token",
            "/validate-token/{token}"
    );

    Predicate<ServerHttpRequest> predicate = serverHttpRequest -> endpoints.stream()
            .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
