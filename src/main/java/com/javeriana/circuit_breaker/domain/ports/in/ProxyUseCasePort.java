package com.javeriana.circuit_breaker.domain.ports.in;

import java.util.List;

public interface ProxyUseCasePort {

    List<Object> consumeInternalApi() throws Exception;
}
