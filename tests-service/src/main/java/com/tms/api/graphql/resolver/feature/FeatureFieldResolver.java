package com.tms.api.graphql.resolver.feature;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tms.api.data.dto.FeatureDto;
import com.tms.api.data.dto.ScenarioDto;
import com.tms.api.model.scenario.ScenarioResponse;

import java.util.Collections;
import java.util.List;

public class FeatureFieldResolver implements GraphQLResolver<ScenarioResponse> {

    List<ScenarioDto> getScenarios(FeatureDto featureDto) {
        return Collections.singletonList(ScenarioDto.builder().scenarioId("someId").build());
    }
}
