package com.zlead.entity.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwRoles {
    private Long rolesId;

    private String rolesName;

    private String rolesDescribe;

    private Long companyId;

    private String userId;

    private Long shopId;

    private Long permissionId;

    private String departmentRemark;

    private Long createTime;

    private Long updateTime;

}