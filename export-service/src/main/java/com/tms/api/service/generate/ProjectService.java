package com.tms.api.service.generate;

import com.tms.api.service.feature.model.FeatureResponse;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface ProjectService {
    InputStreamResource createProject(String groupId, String artifactId, String packageName);
    List<FeatureResponse> createFiles();



}
