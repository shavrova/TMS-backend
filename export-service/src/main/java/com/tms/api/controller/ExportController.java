package com.tms.api.controller;

import com.tms.api.service.feature.model.FeatureResponse;
import com.tms.api.service.generate.ProjectService;
import com.tms.api.service.generate.ProjectServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
public class ExportController {
    @Autowired
    ProjectServiceImpl projectService;

//    @GetMapping("/generate")
//    public ResponseEntity<String> createProject() {
//        return ResponseEntity.ok(projectService.createProject(null, null, null));
//    }

    @GetMapping("/clear")
    public ResponseEntity<String> clear() throws IOException {
        FileUtils.cleanDirectory(new File("export-service/src/main/resources"));
        return ResponseEntity.ok("cleared");
    }


    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> download(
            @RequestParam String groupId,
            @RequestParam String artifactId,
            @RequestParam String packageName
    ) {
        projectService.createProject(groupId, artifactId, packageName);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentDispositionFormData("attachment", artifactId + ".zip");
        respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(projectService.createProject(groupId, artifactId, packageName), respHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/getFeatures")
    public ResponseEntity<List<FeatureResponse>> getFeatures() {
        return ResponseEntity.ok(projectService.createFiles());
    }
}