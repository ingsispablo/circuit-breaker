package com.javeriana.circuit_breaker.infraestructure.outbound.external;

import com.javeriana.circuit_breaker.domain.ports.out.ExternalServicePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ExternalServiceAdapter implements ExternalServicePort {

    private final Logger LOG = LoggerFactory.getLogger(ExternalServiceAdapter.class);

    private final RestTemplate restTemplate;

    /*public ExternalServiceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    public List<Object> consumeExternalApi() throws Exception {
        LOG.info("[consumeApi(...)] - Iniciamos consulta de paises");
        String url = "https://restcountries.com/v3.1/all";
        try {
            LOG.info("[consumeApi(...)] - Inicia validación de estado del servicio");
            if (new Random().nextBoolean()) {
                throw new Exception();
            }

            LOG.info("[consumeApi(...)] - Servicio activo, se procede a llamar a la api: {}", url);
            Object[] countries = restTemplate.getForObject(url, Object[].class);
            LOG.info("[consumeApi(...)] - Api: {} consumida exitosamente", url);
            return countries == null ? new ArrayList<>() : Arrays.stream(countries).toList().subList(1, 10);
        } catch (Exception e) {
            LOG.info("[consumeApi(...)] - Api: [{}] no disponible", url);
            throw new Exception("Failed to fetch countries from the API");
        }
    }
}