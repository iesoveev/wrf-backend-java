package com.wrf.backend.model.response;

import com.alibaba.fastjson.JSON;
import com.wrf.backend.exception.ErrorMessage;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class Response implements Serializable {

    private final String message;
    private final boolean success;

    public Response(ErrorMessage error) {
        this.message = error.getMessage();
        this.success = false;
    }

    public Response(String message) {
        this.message = message;
        this.success = false;
    }

    public Response() {
        this.message = null;
        this.success = true;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        Map<String, Object> outMap = new HashMap<>();
        outMap.put("success", this.message == null);
        outMap.put("message", this.message);
        byte[] json =  JSON.toJSONString(outMap).getBytes(StandardCharsets.UTF_8);
        return new String(json, StandardCharsets.UTF_8);
    }
}

