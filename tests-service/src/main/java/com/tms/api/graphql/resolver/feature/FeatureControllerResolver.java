package com.tms.api.graphql.resolver.feature;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tms.api.data.dto.FeatureDto;
import com.tms.api.data.entity.Feature;
import com.tms.api.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureControllerResolver implements GraphQLQueryResolver {

    @Autowired
    private FeatureService service;

    public FeatureDto getFeature(String featureId) {
        return FeatureDto.builder().featureId("test").featureName("testanme").featureDescription("desc").build();
        //return service.getById(featureId);
    }
}
