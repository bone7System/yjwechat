package com.yj.pojo;

public class ReSult<T> {
    private Long code=200L;
    private String massage;
    private T data;

    public static ReSult success(Object o){
        return new ReSult(200L,"操作成功",o);
    }
    public static ReSult success(){
        return new ReSult(200L,"操作成功",null);
    }
    public static ReSult error(String massage,Object o){
        return new ReSult(500L,massage,o);
    }
    public static ReSult error(Long code,String massage){
        return new ReSult(code,massage,null);
    }

    public ReSult(Long code, String massage, T data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public ReSult(T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
