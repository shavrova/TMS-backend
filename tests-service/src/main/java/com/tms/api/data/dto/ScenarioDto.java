package com.tms.api.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tms.api.data.entity.Feature;
import com.tms.api.data.entity.Step;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScenarioDto implements Serializable {
    private String scenarioId;
    private String scenarioName;
    private String scenarioDescription;
    private Date createdAt;
    private String userId;
    private String featureId;
    private List<StepDto> steps;
}
