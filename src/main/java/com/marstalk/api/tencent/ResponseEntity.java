package com.marstalk.api.tencent;

import java.io.Serializable;

public class ResponseEntity<T> implements Serializable {

    // ================================================================
    // Constants
    // ================================================================

    private static final long serialVersionUID = 1L;

    // ================================================================
    // Fields
    // ================================================================

    private Integer ret;
    private String msg;
    private OCRData data;


    // ================================================================
    // Constructors
    // ================================================================

    // ================================================================
    // Methods from/for super Interfaces or SuperClass
    // ================================================================

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    // ================================================================
    // Public or Protected Methods
    // ================================================================


    // ================================================================
    // Getter & Setter
    // ================================================================

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OCRData getData() {
        return data;
    }

    public void setData(OCRData data) {
        this.data = data;
    }


    // ================================================================
    // Private Methods
    // ================================================================


    // ================================================================
    // Inner or Anonymous Class
    // ================================================================

    // ================================================================
    // Test Methods
    // ================================================================

}
