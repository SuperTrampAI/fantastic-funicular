package com.github.supertrampai.fantasticfunicular;

public class Resp<T> {
    private int code;
    private String msg;
    private T data = null;

    public final static int SUCCESS = 0;
    public final static int FAILURE = 1;
    public final static int ERROR = -1;

    public Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp() {
        this.code = SUCCESS;
        this.msg = "SUCCESS";
    }

    public Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp(T data) {
        this.code = SUCCESS;
        this.msg = "SUCCESS";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ", \"msg\":\"" + msg + "\"}";
    }
    

   /* public static Resp<Object> success(){
		return new Resp<Object>(SUCCESS,"SUCCESS");
    }
    public static Resp<Object> successData(Object data){
		return new Resp<Object>(data);
    }
    
    public static Resp<Object> errorMsg(String msg){
		return new Resp<Object>(ERROR,msg);
    }*/
}

