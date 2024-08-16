package com.javeriana.circuit_breaker.infraestructure.inbound.controllers;

import com.javeriana.circuit_breaker.domain.ports.in.ProxyUseCasePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProxyApiController {

    private final Logger LOG = LoggerFactory.getLogger(ProxyApiController.class);

    private final ProxyUseCasePort proxyUseCasePort;

    @GetMapping("/proxy")
    public List<Object> apiBridge() throws Exception {
        return proxyUseCasePort.consumeInternalApi();
    }

}