package com.bubble.domain;

/**
 * 接口返回结果
 *
 * @author wugang
 * date: 2019-04-01 17:59
 **/
public class ResultData {
    private int code; // 0：失败；1：成功
    private String msg;
    private Object data;

    public enum Code {
        SUCCESS(1),
        FAILED(0);

        private int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
