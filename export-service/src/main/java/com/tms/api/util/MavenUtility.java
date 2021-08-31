package com.tms.api.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MavenUtility {

    public static boolean runMavenTask(String groupId, String artifactId, String packageName, String outputDirectory) {
        List<String> list = new ArrayList<>();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(String.format("mvn archetype:generate " +
                    "\"-DarchetypeGroupId=io.cucumber\" " +
                    "\"-DarchetypeArtifactId=cucumber-archetype\" " +
                    "\"-DarchetypeVersion=6.7.0\" " +
                    "\"-DgroupId=%1$s\" " +
                    "\"-DartifactId=%2$s\" " +
                    "\"-Dpackage=%3$s\" " +
                    "\"-Dversion=1.0.0-SNAPSHOT\" " +
                    "\"-DinteractiveMode=false\" " +
                    "\"-DoutputDirectory=%4$s\"", groupId, artifactId, packageName, outputDirectory) );
        } catch (IOException e) {
            log.error("Error while executing command.");
            e.printStackTrace();
        }
        InputStream inputStream = process.getInputStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String str;
            while ((str = br.readLine()) != null)
                list.add(str);
            process.waitFor();
        } catch (IOException | InterruptedException ex) {
            log.error("Error occurred: " + ex);
        }
        list.forEach(log::info);
        return list.stream().anyMatch(l -> l.contains(Constant.BUILD_SUCCESS));
    }

}
