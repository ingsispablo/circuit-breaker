package com.javeriana.circuit_breaker.domain.ports.in;

import java.util.List;

public interface InternalServicePort {
    List<Object> consumeInternalApi() throws Exception;
}
