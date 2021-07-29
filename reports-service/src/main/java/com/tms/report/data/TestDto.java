package com.tms.report.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TestDto {
    private String id;
    private String name;
    // TODO: enum or class (same as for ReportDto)
    private String status;
}
