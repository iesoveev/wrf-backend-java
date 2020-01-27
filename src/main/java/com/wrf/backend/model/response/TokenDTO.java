package com.wrf.backend.model.response;

import java.io.Serializable;

public final class TokenDTO implements Serializable {

    private final String accessToken;

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
