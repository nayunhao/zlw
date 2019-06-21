package com.zlead.global;

/**
 * 状态码
 * 
 * @author yangting
 *         
 */
public enum States {
    OK( GlobalConstants.STATE_CODE_OK, GlobalConstants.STATE_MSG_OK ), 
    FAIL( GlobalConstants.STATE_CODE_FAIL, GlobalConstants.STATE_MSG_FAIL ),
    NULL( GlobalConstants.STATE_CODE_NULL, GlobalConstants.STATE_MSG_NULL ),
    UNSUPPORT_MIME(GlobalConstants.STATE_CODE_UNSUPPORT_MIME,GlobalConstants.STATE_MSG_UNSUPPORT_MIME);
                            
    private int    code;
    private String msg;
                   
    private States(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }
    
    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
