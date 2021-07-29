package com.tms.report.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tms.report.data.FailureDto;
import com.tms.report.data.ReportDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class TestControllerResolver implements GraphQLQueryResolver {

    public TestDto getTest(String id) {
        //TODO: get report by id from storage
        return TestDto.builder()
                .id(UUID.randomUUID().toString())
                .name("Login as free user")
                .status("failed")
                .build();
    }
}
