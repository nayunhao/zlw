package com.zlead.security;

/**
 * 加解密算法异常类
 *
 * @author Administrator
 * @version 2015-4-4 17:20:11
 */
public class CodecException extends Exception
{
    private static final long serialVersionUID = 3966129708775022345L;

    public CodecException()
    {
        super();
    }

    public CodecException(String msg)
    {
        super(msg);
    }

    public CodecException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public CodecException(Throwable cause)
    {
        super(cause);
    }
}
