package com.tms.api.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

@Slf4j
public class FilesUtility {
    public static void createDirectory(String dirPath) {
        try {
            if (dirPath != null && !"".equals(dirPath.trim())) {
                Path pathObj = Paths.get(dirPath);

                if (Files.notExists(pathObj)) {
                    Files.createDirectory(pathObj);
                    log.info("Create " + dirPath + " success. ");
                } else {
                    FileUtils.cleanDirectory(new File(dirPath));
                }
            }
        } catch (IOException ex) {
            log.error("Exception occurred: " + ex);
        }
    }


//    public static void removeDirectory(String dirPath) {
//        try {
//            if (dirPath != null && !"".equals(dirPath.trim())) {
//                Path pathObj = Paths.get(dirPath);
//                Files.deleteIfExists(pathObj);
//                log.info("Directory " + dirPath + " removed. ");
//            } else
//                log.error("Invalid path");
//        } catch (IOException ex) {
//            log.error("Exception occurred: " + ex);
//        }
//    }

    public static void removeDirectory(String dirPath) {
        try {
            if (dirPath != null && !"".equals(dirPath.trim())) {
                Path pathToBeDeleted = Paths.get(dirPath);
                Files.walk(pathToBeDeleted)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } else
                log.error("Invalid path.");
        } catch (IOException ex) {
            log.error("Exception occurred: " + ex);
        }
    }
}
