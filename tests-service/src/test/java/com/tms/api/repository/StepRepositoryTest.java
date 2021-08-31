package com.tms.api.repository;

import com.tms.api.data.entity.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static com.tms.api.util.ObjectUtil.createStepObject;

public class StepRepositoryTest extends RepositorySetup {

    @Test
    public void whenFindStepById_thenReturned(SoftAssertions softly) {
        Step step = stepRepository.findByStepId("1").get();
        softly.assertThat(step.getStepId()).isEqualTo("1");
        softly.assertThat(step.getStepName()).isEqualTo("Login user");
        softly.assertThat(step.getComment()).isEqualTo("comment");
        softly.assertThat(step.getMethodName()).isEqualTo("login");
        softly.assertThat(step.getCreatedAt()).hasSameTimeAs("2012-09-17 18:47:52.069");
        softly.assertThat(step.getUpdatedAt()).hasSameTimeAs("2021-09-17 18:47:12.069");
    }


    @Test
    public void whenFindByStepName_thenReturned(SoftAssertions softly) {
        Step step = stepRepository.findByStepName("Request processing status").get();
        softly.assertThat(step.getStepName()).isEqualTo("Request processing status");
        softly.assertThat(step.getComment()).isEqualTo("comment");
        softly.assertThat(step.getMethodName()).isEqualTo("requestStatus");
        softly.assertThat(step.getCreatedAt()).hasSameTimeAs("2012-09-17 18:12:52.069");
        softly.assertThat(step.getUpdatedAt()).hasSameTimeAs("2021-09-17 18:47:52.069");
    }

    @Test
    public void whenStepIsSaved_thenSuccess(SoftAssertions softly) {
        Step step = createStepObject();
        Step saved = stepRepository.save(step);
        softly.assertThat(saved).isNotNull();
        softly.assertThat(step).isEqualToComparingFieldByField(saved);
    }
}
