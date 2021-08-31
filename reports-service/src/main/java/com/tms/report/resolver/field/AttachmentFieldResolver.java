package com.tms.report.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.tms.report.data.AttachmentDto;
import com.tms.report.data.ReportDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttachmentFieldResolver implements GraphQLResolver<AttachmentDto> {

    public ReportDto getReport(AttachmentDto dto) {
        return ReportDto.builder()
                .id(UUID.randomUUID().toString())
                .status("passed")
                .comment("comment")
               // .attachmentId(dto.getId())
                .build();
    }
}
