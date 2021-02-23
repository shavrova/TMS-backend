package com.tms.api.service.feature.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class StepResponse {

    private String stepId;

    private String stepName;

    private String comment;

    private String methodName;

    private Date createdAt;

    private Date updatedAt;

}
