package com.tms.api.users.service.test;

import com.tms.api.users.data.model.test.ScenarioResponseModel;
import com.tms.api.users.service.test.fallback.TestsFallbackFactory;
import com.tms.api.users.util.Constant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = Constant.TEST_SERVICE_NAME, fallbackFactory = TestsFallbackFactory.class)
public interface TestServiceClient {
    @GetMapping(Constant.TESTS_PATH + "/{userId}")
    List<ScenarioResponseModel> getTests(@PathVariable("userId") String userId);
}
