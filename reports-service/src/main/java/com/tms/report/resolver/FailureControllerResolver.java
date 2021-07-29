package com.tms.report.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tms.report.data.FailureDto;
import com.tms.report.data.ReportDto;
import com.tms.report.data.TestDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
public class FailureControllerResolver implements GraphQLQueryResolver {

    public FailureDto getFailure(String id) {
        //TODO: get from storage
        return FailureDto.builder()
                .failureId(UUID.randomUUID().toString())
                .message("Can't find login button")
                .stacktrace("Executing pre-compile tasks...\n" +
                        "Running 'before' tasks\n" +
                        "Checking sources\n" +
                        "Copying resources... [reports-service]\n" +
                        "Parsing java... [reports-service]\n" +
                        "java: Errors occurred while compiling module 'reports-service'\n" +
                        "Checking dependencies... [reports-service]\n" +
                        "Dependency analysis found 0 affected files\n" +
                        "javac 11.0.8 was used to compile java sources\n" +
                        "Finished, saving caches...\n" +
                        "Compilation failed: errors: 1; warnings: 0\n" +
                        "Executing post-compile tasks...\n" +
                        "Synchronizing output directories...\n" +
                        "24.07.2021, 13:53 - Build completed with 1 error and 0 warnings in 4 s 251 ms\n")
                .screenshotPath("/Users/scr/scr1.png")
                .build();
    }
}
