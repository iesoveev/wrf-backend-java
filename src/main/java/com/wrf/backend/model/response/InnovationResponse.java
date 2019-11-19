package com.wrf.backend.model.response;

public final class InnovationResponse {

    private final String innovationId;

    public InnovationResponse(String id) {
        this.innovationId = id;
    }

    public String getInnovationId() {
        return innovationId;
    }
}
