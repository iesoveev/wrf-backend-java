package com.wrf.backend.model.response;

import java.io.Serializable;

public final class TokenModel implements Serializable {

    private final String accessToken;

    public TokenModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
