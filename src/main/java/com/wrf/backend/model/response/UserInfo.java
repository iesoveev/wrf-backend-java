package com.wrf.backend.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class UserInfo {

    private final String phone;

    private final String id;
}
