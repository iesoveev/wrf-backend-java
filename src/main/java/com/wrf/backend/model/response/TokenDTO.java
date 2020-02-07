package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public final class TokenDTO implements Serializable {

    private final String accessToken;
}
