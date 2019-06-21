package com.zlead.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApiRequest {
    private String platform;
    private String token;
    private String appVersion;
    private String apiVersion;

    /**
     * 设备ID
     */
    private String imei;

    /**
     * 接口签名
     */
    private String signature;

    /**
     * 具体的请求参数
     */
    private Map data;
}
