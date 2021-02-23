package com.tms.api.service.feature.fallback;

import com.tms.api.service.feature.FeatureServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeaturesFallbackFactory implements FallbackFactory<FeatureServiceClient> {

    @Override
    public FeatureServiceClient create(Throwable throwable) {
        return new FeatureServiceClientFallback(throwable);
    }
}
