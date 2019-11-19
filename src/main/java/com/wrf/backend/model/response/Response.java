package com.wrf.backend.model.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class Response implements Serializable {

    private final Integer code;
    private final String message;
    private final Object data;

    public Response(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Response() {
        this.code = null;
        this.message = null;
        this.data = null;
    }

    public byte[] toJson() {
        Map<String, Object> outMap = new HashMap<>();
        outMap.put("code", this.code);
        outMap.put("success", this.code == null);
        outMap.put("message", this.message);
        outMap.put("data", this.data);
        return JSON.toJSONString(outMap).getBytes(StandardCharsets.UTF_8);
    }

    public String toString() {
        return new String(toJson(), StandardCharsets.UTF_8);
    }

}

