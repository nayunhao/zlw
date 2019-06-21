package com.zlead.entity.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwDepartment {
    private Long departmentId;

    private String departmentName;

    private String userId;

    private Long companyId;

    private Long shopId;

    private String departmentRemark;

    private Long createTime;

    private Long updateTime;

}