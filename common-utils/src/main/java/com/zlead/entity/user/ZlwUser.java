package com.zlead.entity.user;
import com.zlead.entity.company.ZlwCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
*
*  @author author
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ZlwUser implements Serializable {

    private static final long serialVersionUID = 1558765194809L;


    /**
    * 主键
    * 主键编号
    * isNullAble:0
    */
    private String userId;

    /**
    * 用户名
    * isNullAble:0
    */
    private String userName;

    /**
    * isNullAble:0
    */
    private String userPassword;

    /**
    * isNullAble:1
    */
    private String userRealname;

    /**
    * 
    * isNullAble:1
    */
    private Integer userGender;

    /**
    * 
    * isNullAble:0
    */
    private String userPhone;

    /**
    * 
    * isNullAble
    */
    private String userEmail;

    /**
    * 
    * isNullAble:1
    */
    private String userAddress;

    /**
    * 
    * isNullAble:1
    */
    private String userPhoto;

    /**
    * 
    * isNullAble:1
    */
    private String userIntoduction;

    /**
    * 用户注册类型
    * isNullAble:1
    */
    private Integer userRegtypeId;

    /**
    * 注册IP
    * isNullAble:1
    */
    private String userRegip;

    /**
    * 授权审核码
    * isNullAble:1
    */
    private String userAuthorcode;

    /**
    * 注册时间
    * isNullAble:1
    */
    private Long userRegtime;

    /**
    * 是否锁定 0：未锁定，1：锁定
    *
    */
    private Integer userIsLock;


    /**
    * 是否删除
    * isNullAble:1
    */
    private Integer userIsDelete;

    /**
    * 身份证号
    * isNullAble:1
    */
    private String userIdcard;

    /**
    * 
    * isNullAble:1
    */
    private String userIdcardAddress;

    /**
    * 
    * isNullAble:1
    */
    private String userRemark;

    /**
    * 
    * isNullAble:1
    */
    private Long createTime;

    /**
    * 
    * isNullAble:1
    */
    private Long updateTime;


    private List<ZlwCompany> zlwCompanyList;

}
