package com.zlead.global;

/**
 * 全局常量定义
 * 
 * @author yangting
 *         
 */
public class GlobalConstants {
    /**
     * 日期格式
     */
    public static final String  DATE                            = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    public static final String  DATETIME                        = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间戳格式
     */
    public static final String  TIMESTAMP                       = "yyyy-MM-dd HH:mm:ss.SSS";
                                                                
    /**
     * 状态码：成功
     */
    public static final int     STATE_CODE_OK                   = 6000;
    /**
     * 状态码：失败
     */
    public static final int     STATE_CODE_FAIL                 = 9000;
    /**
     * 状态码：服务连接失败
     */
    public static final int     STATE_CODE_THRIFT_CONNECT_ERROR = 9001;
    /**
     * 状态码：空值错误
     */
    public static final int     STATE_CODE_NULL                 = 9002;
    /**
     * 状态码：不支持的MIME类型
     */
    public static final int     STATE_CODE_UNSUPPORT_MIME       = 9003;
    /**
     * 状态信息：成功
     */
    public static final String  STATE_MSG_OK                    = "OK";
    /**
     * 状态信息：失败
     */
    public static final String  STATE_MSG_FAIL                  = "FAIL";
    /**
     * 状态信息：空值
     */
    public static final String  STATE_MSG_NULL                  = "NULL";
    /**
	 * 状态信息：不支持的MIME类型
	 */
	public static final String STATE_MSG_UNSUPPORT_MIME = "UNSUPPORT MIME";
    /**
     * 状态信息：服务连接错误
     */
    public static final String  STATE_MSG_THRIFT_CONNECT_ERROR  = "THRIFT CONNECT ERROR";
    /**
     * 服务名：调试
     */
    public static final String  SERVICE_DEBUG                   = "DebugServiceIface";
}
