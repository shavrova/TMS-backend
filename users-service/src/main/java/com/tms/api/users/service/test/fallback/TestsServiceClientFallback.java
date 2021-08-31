package com.tms.api.users.service.test.fallback;

import com.tms.api.users.data.model.test.ScenarioResponseModel;
import com.tms.api.users.service.test.TestServiceClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestsServiceClientFallback implements TestServiceClient {

    private final Throwable cause;

    public TestsServiceClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<ScenarioResponseModel> getTests(String userId) {
        if (cause instanceof FeignException) {
            log.error(String.format("Feign exception occurred. Status: %1$s. Message: %2$s", ((FeignException) cause).status(), cause.getMessage()));
        } else {
            log.error(String.format("Something went wrong. Message: %s", cause.getMessage()));
        }
        return new ArrayList<>();
    }
}
