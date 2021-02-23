package com.tms.api.users.service.test.fallback;

import com.tms.api.users.service.test.TestServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TestsFallbackFactory implements FallbackFactory<TestServiceClient> {

    @Override
    public TestServiceClient create(Throwable throwable) {
        return new TestsServiceClientFallback(throwable);
    }
}
