package com.tms.report.data;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDto implements Serializable {
    private String id;
    //TODO: create enum or class
    private String status;
   // private Date createTime;
    private String comment;
   // private List<TestDto> tests;
    //TODO:
    //private List<Feature> features;
}
