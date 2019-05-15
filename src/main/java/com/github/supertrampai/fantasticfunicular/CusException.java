package com.github.supertrampai.fantasticfunicular;


public class CusException extends RuntimeException {
    private static final long serialVersionUID = 5402012883466443408L;
    private Integer code;
    private String msg;

    public CusException() {
    }

    public CusException(Integer code) {
        this(code, (String) null);
    }

    public CusException(String message) {
        this(1, message);
    }

    public CusException(Integer code, String message) {
        this(code, message, (Throwable) null);
    }

    public CusException(Integer code, Throwable cause) {
        this(code, (String) null, cause);
    }

    public CusException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = message;
    }
}