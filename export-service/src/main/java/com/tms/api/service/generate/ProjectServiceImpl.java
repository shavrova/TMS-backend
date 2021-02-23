package com.tms.api.service.generate;

import com.tms.api.service.feature.FeatureServiceClient;
import com.tms.api.service.feature.model.FeatureResponse;
import com.tms.api.util.ZipUtility;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static com.tms.api.util.FilesUtility.createDirectory;
import static com.tms.api.util.FilesUtility.removeDirectory;
import static com.tms.api.util.MavenUtility.runMavenTask;

@Slf4j
@Service
public class ProjectServiceImpl {

    @Autowired
    FeatureServiceClient featureServiceClient;


    @SneakyThrows
    public InputStreamResource createProject(String groupId, String artifactId, String packageName) {
        String outputDirectory = "export-service/temp";
        createDirectory(outputDirectory);
        runMavenTask(groupId, artifactId, packageName, outputDirectory);
        String zippedFilePath = zipProject(outputDirectory, artifactId);
        InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(zippedFilePath)));
        removeDirectory(outputDirectory);
        return isr;
    }


    private void getData() {
    }


    private String zipProject(String outputDirectory, String artifactId) {
        File projectDir = new File(outputDirectory + "/" + artifactId);
        String destination = outputDirectory + "/" + artifactId + ".zip";

        ZipUtility zipUtil = new ZipUtility();
        try {
            zipUtil.zip(projectDir, destination);
        } catch (Exception ex) {
            log.error("Error occured: " + ex);
            ex.printStackTrace();
        }
        return destination;
    }

    public List<FeatureResponse> createFiles() {
        //   List<FeatureResponse> featureResponse =
        return featureServiceClient.getAllFeatures();
    }
}
