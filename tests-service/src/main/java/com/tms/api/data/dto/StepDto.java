package com.tms.api.data.dto;

import lombok.*;

import javax.persistence.NamedEntityGraph;
import java.io.Serializable;
import java.util.Date;

@Data
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepDto implements Serializable {
    private String stepId;

    private String stepName;

    private String comment;

    private String methodName;

    private Date createdAt;

    private Date updatedAt;

}
