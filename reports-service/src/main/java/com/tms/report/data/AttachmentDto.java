package com.tms.report.data;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttachmentDto implements Serializable {
    private String id;
    private String name;
    private String description;
    private String path;
    private String reportId;
}
