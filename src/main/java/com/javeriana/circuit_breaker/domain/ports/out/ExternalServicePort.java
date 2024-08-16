package com.javeriana.circuit_breaker.domain.ports.out;

import java.util.List;

public interface ExternalServicePort {
    List<Object> consumeExternalApi() throws Exception;
}
