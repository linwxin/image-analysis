package com.imageanalysis.demo.VO;

import lombok.Data;

@Data
public class HttpRequest<T> {
    private boolean msg;

    private int code;

    private T data;

    public HttpRequest(boolean msg, T data) {
        this.code = 200;
        this.data = data;
        this.msg = msg;
    }

    public HttpRequest(boolean msg) {
        this.code = 200;
        this.data = null;
        this.msg = msg;
    }
}
