package com.javeriana.circuit_breaker.application.proxy;

import com.javeriana.circuit_breaker.domain.ports.in.ProxyUseCasePort;
import com.javeriana.circuit_breaker.domain.ports.in.InternalServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyUseCase implements ProxyUseCasePort {

    private InternalServicePort InternalServicePort;

    public List<Object> consumeInternalApi() throws Exception {
        return this.InternalServicePort.consumeInternalApi();
    }

}
