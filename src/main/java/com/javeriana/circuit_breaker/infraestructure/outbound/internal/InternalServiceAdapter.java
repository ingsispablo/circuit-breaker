package com.javeriana.circuit_breaker.infraestructure.outbound.internal;

import com.javeriana.circuit_breaker.domain.ports.in.InternalServicePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternalServiceAdapter implements InternalServicePort {

    private final Logger LOG = LoggerFactory.getLogger(InternalServiceAdapter.class);

    private final RestTemplate restTemplate;

    @Override
    public List<Object> consumeInternalApi() throws Exception {
        LOG.info("[consumeInternalApi(...)] - Inicia consulta de paises");
        Object[] countries = null;
        String url = "http://localhost:9090/proxy";
        try {
            LOG.info("[consumeInternalApi(...)] - Se procede a llamar a la api: {}", url);
            countries = restTemplate.getForObject(url, Object[].class);
            if (countries == null) {
                LOG.info("[consumeInternalApi(...)] - Se retorna la lista Vacia.");
                return new ArrayList<>();
            }

            LOG.info("[consumeInternalApi(...)] - Api: {} consumida exitosamente, se retornan [{}] paises", url, countries.length);
            return Arrays.stream(countries).toList();
        } catch (Exception e) {
            LOG.info("[consumeInternalApi(...)] - Api: [{}] no disponible", url);
            throw new Exception("Failed to fetch countries from the API");
        }
    }
}