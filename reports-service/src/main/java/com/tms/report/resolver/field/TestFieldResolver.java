package com.tms.report.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tms.report.data.FailureDto;
import com.tms.report.data.ReportDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class TestFieldResolver implements GraphQLResolver<TestDto> {
    public FailureDto failure(TestDto dto) {
        return FailureDto.builder()
                .failureId(UUID.randomUUID().toString())
                .message("Can't find login button")
                .stacktrace("Executing pre-compile tasks..")
                .screenshotPath("/Users/scr/scr1.png")
                .testId(dto.getId())
                .build();
    }
}
