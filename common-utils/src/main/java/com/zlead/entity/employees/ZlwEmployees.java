package com.zlead.entity.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwEmployees {

    private Long employeesId;

    private String userId;

    private String employeesBorthday;

    private String employeesIdcard;

    private String employeesIdcardAddress;

    private String employeesEducation;

    private String employeesJobs;

    private Long shopId;

    private String employeesRemark;

    private Long createTime;

    private Long updateTime;

}