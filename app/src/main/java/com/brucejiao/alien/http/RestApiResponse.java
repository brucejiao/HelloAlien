package com.brucejiao.alien.http;

/**
 * Created by tiansj on 2016/11/30.
 */

public class RestApiResponse {

    private String msg;
    private String success;
    private String value;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
