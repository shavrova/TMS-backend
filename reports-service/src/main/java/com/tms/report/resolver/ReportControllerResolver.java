package com.tms.report.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tms.report.data.FailureDto;
import com.tms.report.data.ReportDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReportControllerResolver implements GraphQLQueryResolver {

    public ReportDto getReport(String id) {
        //TODO: get report by id from storage
        return ReportDto.builder()
                .id(UUID.randomUUID().toString())
                .comment("Cool report")
                .status("Failed")
                .build();
    }
}
