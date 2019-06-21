package com.zlead.entity.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwShop {
    private Long shopId;

    private String shopName;

    private String shopRegion;

    private String shopAddress;

    private Long shopRegtime;

    private String shopContact;

    private String shopTel;

    private String shopEmail;

    private String shopIntroduction;

    private String shopLogo;

    private String userId;

    private String companyId;

    private Long createTime;

    private Long updateTime;

    private String shopRemark;

}