package com.tms.api.service.feature.fallback;

import com.tms.api.service.feature.FeatureServiceClient;
import com.tms.api.service.feature.model.FeatureResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FeatureServiceClientFallback implements FeatureServiceClient {

    private final Throwable cause;

    public FeatureServiceClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<FeatureResponse> getAllFeatures() {
        if (cause instanceof FeignException) {
            log.error(String.format("Feign exception occurred. Status: %1$s. Message: %2$s", ((FeignException) cause).status(), cause.getMessage()));
        } else {
            log.error(String.format("Something went wrong. Message: %s", cause.getMessage()));
        }
        return new ArrayList<>();
    }
}
