package com.wrf.backend.model.response;

public final class InnovationDTO {

    private final String innovationId;

    public InnovationDTO(String id) {
        this.innovationId = id;
    }

    public String getInnovationId() {
        return innovationId;
    }
}
