package com.tms.api.repository;

import com.tms.api.data.entity.Feature;
import com.tms.api.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static com.tms.api.util.ObjectUtil.createFeatureObject;
import static com.tms.api.util.ObjectUtil.createFeatureObjectWithScenario;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DataJpaTest
public class FeatureRepositoryTest extends RepositorySetup {

    @Test
    public void whenFeatureIsSaved_thenSuccess() {
        Feature feature = createFeatureObject();
        Feature saved = featureRepository.save(feature);
        assertNotNull(saved);
        assertEquals(feature.getFeatureName(), saved.getFeatureName());
        assertEquals(feature.getFeatureDescription(), saved.getFeatureDescription());
        assertEquals(feature.getClassName(), saved.getClassName());
        assertThat(saved).isEqualTo(feature);
    }

    @Test
    public void whenFeatureIsSavedWithoutName_thenFail() {
        Feature feature = Feature.builder()
                .featureId(IdUtil.uuid())
                .build();
        assertThatThrownBy(() -> featureRepository.save(feature))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void whenFeatureIsSavedWithScenario_thenPersistedSuccessfully() {
        Feature feature = createFeatureObjectWithScenario();
        Feature saved = featureRepository.save(feature);
        assertThat(saved).isEqualToComparingFieldByField(feature);
        featureRepository.findByFeatureId(saved.getFeatureId());
    }

    @Test
    public void whenFeatureIsSavedWithExistingId_thenFail() {
        Feature feature = Feature.builder()
                .featureId("1")
                .featureName(IdUtil.uuid())
                .build();
        assertThatThrownBy(() -> featureRepository.save(feature))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void whenFeatureIsSavedWithoutId_thenFail() {
        Feature feature = createFeatureObject();
        feature.setFeatureId(null);
        assertThatThrownBy(() -> featureRepository.save(feature))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void whenFeatureIsUpdated_thenSuccess(SoftAssertions softly) {
        Feature feature = featureRepository.findByFeatureId("1").get();
        String newName = IdUtil.uuid();
        String newDescription = IdUtil.uuid();
        String newClassName = IdUtil.uuid();
        feature.setFeatureName(newName);
        feature.setFeatureDescription(newDescription);
        feature.setClassName(newClassName);
        featureRepository.save(feature);
        Feature updated = featureRepository.findByFeatureId("1").get();
        softly.assertThat(newName).isEqualTo(updated.getFeatureName());
        softly.assertThat(newDescription).isEqualTo(updated.getFeatureDescription());
        softly.assertThat(newClassName).isEqualTo(updated.getClassName());
    }

    @Test
    public void whenFindFeatureById_thenReturned(SoftAssertions softly) {
        Feature feature = featureRepository.findByFeatureId("4").get();
        softly.assertThat(feature.getFeatureId()).isEqualTo("4");
        softly.assertThat(feature.getFeatureName()).isEqualTo("Process");
        softly.assertThat(feature.getFeatureDescription()).isEqualTo("This feature enables user to process data into the system");
        softly.assertThat(feature.getClassName()).isEqualTo("ProcessFeature");
    }

    @Test
    public void whenFindByFeatureName_thenReturned(SoftAssertions softly) {
        Feature feature = featureRepository.findByFeatureName("Login").get();
        softly.assertThat(feature.getFeatureName()).isEqualTo("Login");
        softly.assertThat(feature.getFeatureDescription()).isEqualTo("This feature enables user to login into the system");
        softly.assertThat(feature.getClassName()).isEqualTo("LoginFeature");
    }
}
