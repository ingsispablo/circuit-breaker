package com.javeriana.circuit_breaker.domain.ports.in;

import java.util.List;

public interface CountryFindUseCasePort {

    List<Object> findAllCountries() throws Exception;
}
