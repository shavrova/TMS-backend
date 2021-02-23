package com.tms.api.service.feature.model;

import com.tms.api.service.feature.model.ScenarioResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class FeatureResponse {

    private String featureId;

    private String featureName;

    private String featureDescription;

    private String className;

    private List<ScenarioResponse> scenarios;
}
