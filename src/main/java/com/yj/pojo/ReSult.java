package com.yj.pojo;

public class ReSult {
    private Long code=200L;
    private String massage;
    private Object data;

    public static ReSult success(Object o){
        return new ReSult(200L,"操作成功",o);
    }
    public static ReSult error(String massage,Object o){
        return new ReSult(500L,massage,o);
    }
    public ReSult(Long code, String massage, Object data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public ReSult(Object data) {
        this.data = data;
    }

    public ReSult(String massage) {
        this.massage = massage;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
