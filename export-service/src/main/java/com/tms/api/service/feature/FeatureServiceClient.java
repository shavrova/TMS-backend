package com.tms.api.service.feature;

import com.tms.api.service.feature.fallback.FeaturesFallbackFactory;
import com.tms.api.service.feature.model.FeatureResponse;
import com.tms.api.util.Constant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = Constant.TESTS_SERVICE_NAME, fallbackFactory = FeaturesFallbackFactory.class)
public interface FeatureServiceClient {

    @GetMapping(Constant.GET_FEATURES_PATH)
    List<FeatureResponse> getAllFeatures();
}
