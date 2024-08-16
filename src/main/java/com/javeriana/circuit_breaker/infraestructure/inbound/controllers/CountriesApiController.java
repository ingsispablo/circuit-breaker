package com.javeriana.circuit_breaker.infraestructure.inbound.controllers;

import com.javeriana.circuit_breaker.domain.ports.in.CountryFindUseCasePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountriesApiController {

    private final Logger LOG = LoggerFactory.getLogger(CountriesApiController.class);

    private final CountryFindUseCasePort countryFindUseCasePort;

    @GetMapping("/countries")
    @CircuitBreaker(name = "countriesCircuitBreaker", fallbackMethod = "getCountries")
    public List<Object> getCountries() throws Exception {
        return countryFindUseCasePort.findAllCountries();
    }

    public List<Object> getCountries(Throwable throwable) {
        LOG.info("[getCountries(...)] - Ingresa al fallbackMethod en el Service");
        List<Object> countries = new ArrayList<>();
        countries.add("Country service unavailable!");
        return countries;
    }

}