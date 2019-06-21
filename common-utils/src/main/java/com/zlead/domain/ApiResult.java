package com.zlead.domain;

import com.zlead.constant.ReturnCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int CODE_OK = 200;
    public static final int CODE_ERR = 500;

    private int status = ApiResult.CODE_OK; //ok
    private String token;
    private String message;
    private Object data;

    public ApiResult(int status, String token, String message, Object data){
        this.status=status;
        this.token=token;
        this.message=message;
        this.data=data;
    }
    public static ApiResult isOk(String token,String message,Object data){
        return new ApiResult(CODE_OK,token,message,data);
    }

    public static ApiResult isOkNoToken(String message,Object data){
        return new ApiResult(CODE_OK,"",message,data);
    }

    public static ApiResult isErr(String token,String message,Object data){
      return new ApiResult(CODE_ERR,token,message,data);
    }

    public static ApiResult isErrNoToken(String message,Object data){
        return new ApiResult(CODE_ERR,"",message,data);
    }

    public static ApiResult isErrTokenAndMsg(String token,String message){
        return new ApiResult(CODE_ERR,token,message,null);
    }

    public static ApiResult isErrMessage(String message){
        return new ApiResult(CODE_ERR,"",message,null);
    }

    public static ApiResult isErrMessage(ReturnCode returnCode){
        return new ApiResult(CODE_ERR,"",returnCode.getMessage(),null);
    }
    public static ApiResult isErrNoToken(ReturnCode returnCode,Object data){
        return new ApiResult(CODE_ERR,"",returnCode.getMessage(),data);
    }

    public static ApiResult isOkNoToken(ReturnCode returnCode,Object data){
        return new ApiResult(CODE_OK,"",returnCode.getMessage(),data);
    }

//    public static ApiResult successRes(boolean isSuccess){
//        Map map = new HashMap<>();
//        map.put("isSuccess",isSuccess);
//
//    }

}
