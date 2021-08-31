package com.tms.report.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tms.report.data.AttachmentDto;
import com.tms.report.data.ReportDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class ReportFieldResolver implements GraphQLResolver<ReportDto> {

    public List<TestDto> tests(ReportDto dto) {
        TestDto test = TestDto.builder()
                .id(UUID.randomUUID().toString())
                .name("Login as free user")
                .status("failed")
                .build();
        return Collections.singletonList(test);
    }

    public AttachmentDto attachment(ReportDto dto) {
        return AttachmentDto.builder()
                .id(UUID.randomUUID().toString())
                .name("Attachment #2")
                .path("http://localhost:7001/discoveryservice/default")
                .reportId(dto.getId())
                .build();
    }
}
