package com.aquariux.test.cryptocurrencytradingsystem.services.external.impls;

import com.aquariux.test.cryptocurrencytradingsystem.services.external.IRestCallerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
@Slf4j
public class RestCallerService implements IRestCallerService {
    private final RestTemplate restTemplate;

    @Override
    public Object get(String endpoint, String path, Class<?> responseType) {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(endpoint)
                .path(path)
                .build();
        try {
            log.info("Call GET request to {}{}", endpoint, path);
            return restTemplate.getForEntity(uri.encode().toUri(),
                    responseType).getBody();
        } catch (HttpClientErrorException ex) {
            log.error("Error when call api to {}{}: {}", endpoint, path, ex.getMessage());
            return null;
        }
    }
}
