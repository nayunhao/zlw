package com.zlead.entity.company;

import com.zlead.entity.shop.ZlwShop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwCompany {

    private Long companyAddress;
    private String companyBusinessNumber;
    private String companyContact;
    private String companyEmail;
    private String companyId;
    private String companyIntroduction;
    private String companyLegalperson;
    private String companyLogo;
    private String companyName;
    private String companyRegion;
    private Long companyRegtime;
    private String companyRemark;
    private String companyTel;
    private Long createTiem;
    private Long updateTiem;
    private String userId;

    private List<ZlwShop> zlwShopList;
    
    
}
