package com.tms.report.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FailureDto {
    private String failureId;
    private String message;
    private String stacktrace;
    private String screenshotPath;
    private String videoPath;
    private String testId;
}
