package com.wrf.backend.controller;

import com.wrf.backend.model.response.CategoryDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.InnovationDTO;
import com.wrf.backend.service.InnovationService;
import com.wrf.backend.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/innovations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InnovationController {

    private final InnovationService innovationService;

    @Autowired
    public InnovationController(InnovationService innovationService) {
        this.innovationService = innovationService;
    }

    @PostMapping
    public InnovationDTO addInnovation(@RequestBody UserInnovationRequest innovationRequest) throws IllegalAccessException {
        ValidationUtils.validate(innovationRequest);
        return innovationService.addInnovation(innovationRequest);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> categories() {
        return innovationService.getAllCategories();
    }

    @PostMapping("/images")
    @Transactional
    public Response saveImage(@RequestBody ImageRequestModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        innovationService.saveImageByInnovationId(model);
        return new Response();
    }
}
