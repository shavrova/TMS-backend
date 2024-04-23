package com.tms.api.repository;

import com.tms.api.data.repository.FeatureRepository;
import com.tms.api.data.repository.StepRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * Integration testing repository with H2 database
 */
@Slf4j
@DataJpaTest
@ActiveProfiles("integration")
@Sql("classpath:integration-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SoftAssertionsExtension.class)
class RepositorySetup {

    @Autowired
    protected FeatureRepository featureRepository;
    @Autowired
    protected StepRepository stepRepository;
}