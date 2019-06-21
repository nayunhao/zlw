package com.zlead.constant;

public enum ServiceReturnCode implements ReturnCode {
    PHONE_FORMAT_ERROR("U00001", "手机号格式有误，请重新输入"),
    USERNAME_ISNOT_EXIST("U00002", "账号不存在，请确认手机号是否正确"),
    VERIFYCODE_ERROR("U00003", "验证码不正确，请重新输入"),
    VERIFYCODE_EXPIRE("U00004", "验证码已过期，请重新获取"),
    VERIFYCODE_USE_OVER("U00005", "您今日获取验证码次数已用完，验证码有效期30分钟"),
    PHONE_REGISTER("U00006","手机号已经被注册，您可以直接登录"),
    PASSWORD_FORMAT_ERROR("U00007","密码至少要由6位字母|数字|字符的其中两种组成"),
    PASSWORD_NOT_SAME("U00008","密码不一致"),
    PASSWORD_MODIFY_SUCCESS("U00009","密码修改成功"),
    INPUT_SHOP_INFO("U00010","请输入店铺信息"),
    SHOP_INFO_SENSITIVE("U00011","店铺名称包含敏感信息"),
    SHOP_IS_EXIST("U00012","店铺已存在"),
    REDIS_ADD_VERIFYCODE_ERROR("U00013","向redis中添加校验码失败"),
    READ_REDIS_INFO_ERROR("U00014","读取redis中的信息失败"),
    IMAGE_FORMAT_ERROR("P00015","上传图片格式不正确"),
    IMAGE_UPLOAD_ERROR("P00016","图片上传失败"),
    NO_UPLOAD_IMAGE("P00017","未上传图片"),
    IMAGE_UPLOAD_SUCCESS("P00018","图片上传成功"),
    IMPORT_GOODS_ISEMPTY("P00019","导入商品为空"),
    INPUT_PLATFORM_PGID("P00020","请输入平台商品Id");

    private String code;
    private String message;
    private ServiceReturnCode(String code,String message){
        this.code=code;
        this.message=message;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
