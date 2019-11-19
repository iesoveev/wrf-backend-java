package com.wrf.backend.controller;

import com.wrf.backend.model.response.Response;
import com.wrf.backend.entity.InnovationCategory;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.InnovationResponse;
import com.wrf.backend.service.ImageService;
import com.wrf.backend.service.InnovationService;
import com.wrf.backend.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/innovations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InnovationController {

    @Autowired
    InnovationService innovationService;

    @Autowired
    ImageService imageService;

    @PostMapping
    @Transactional
    public InnovationResponse addInnovation(@RequestBody UserInnovationRequest innovationRequest) throws IllegalAccessException {
        ValidationUtils.validate(innovationRequest);
        return innovationService.addInnovation(innovationRequest);
    }

    @GetMapping("/categories")
    public List<InnovationCategory> categories() {
        return innovationService.getAllCategories();
    }

    @PostMapping("/images")
    @Transactional
    public byte[] saveImage(@RequestBody ImageRequestModel model) throws IllegalAccessException {
        ValidationUtils.validate(model);
        innovationService.saveImageByInnovationId(model);
        return new Response().toJson();
    }
}
