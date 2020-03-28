package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenDTO {

    private String accessToken;
}
