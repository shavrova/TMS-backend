package com.tms.api.integration;

import com.tms.api.model.scenario.CreateScenarioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
public class RestTemplateUtil {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> request;

    public <T> T create(String url, String payload, Class<T> clazz) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(payload, headers);
        return restTemplate.postForObject(url, request, clazz);
    }

    /**
     * User id is required to create scenario. See controller implementation for more details.
     * @see com.tms.api.controller.ScenarioController#createScenario(CreateScenarioRequest, String userId)
     * Create feature requests should be send with 'user-id' header
     */
    public <T> T create(String url, String payload, HttpHeaders headers, Class<T> clazz) {
        request = new HttpEntity<>(payload, headers);
        return restTemplate.postForObject(url, request, clazz);
    }

}
