package com.javeriana.circuit_breaker.application.find;

import com.javeriana.circuit_breaker.domain.ports.in.CountryFindUseCasePort;
import com.javeriana.circuit_breaker.domain.ports.out.ExternalServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryFindUseCase implements CountryFindUseCasePort {

    private final ExternalServicePort externalServicePort;

    public CountryFindUseCase(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    public List<Object> findAllCountries() throws Exception {
        return externalServicePort.consumeExternalApi();
    }

}
