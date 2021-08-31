package com.tms.report.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tms.report.data.FailureDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FailureFieldResolver implements GraphQLResolver<FailureDto> {
    public TestDto test(FailureDto dto) {
        return TestDto.builder()
                .id(UUID.randomUUID().toString())
                .name("Login as free user")
                .status("failed inside FailureFieldResolver")
                .build();
    }
}
