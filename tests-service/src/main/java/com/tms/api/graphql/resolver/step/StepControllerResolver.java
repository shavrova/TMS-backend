package com.tms.api.graphql.resolver.step;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tms.api.data.dto.FeatureDto;
import com.tms.api.data.dto.StepDto;
import com.tms.api.service.FeatureService;
import com.tms.api.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StepControllerResolver implements GraphQLQueryResolver {

    @Autowired
    private StepService stepService;

    public StepDto getStep(String stepId) {
        return stepService.getById(stepId);
//        return StepDto.builder()
//                .stepId("id")
//                .stepName("Name")
//                .comment("comment")
//                .createdAt(new Date())
//                .updatedAt(new Date())
//                .build();
    }
}
