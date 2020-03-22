package com.wrf.backend.controller;

import com.wrf.backend.model.response.CategoryDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.model.response.InnovationDTO;
import com.wrf.backend.service.InnovationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/innovations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InnovationController {

    private final InnovationService innovationService;

    public InnovationController(InnovationService innovationService) {
        this.innovationService = innovationService;
    }

    @ApiOperation(value = "Добавить инновацию")
    @PostMapping
    public InnovationDTO addInnovation(@Valid @RequestBody UserInnovationRequest innovationRequest) {
        return innovationService.addInnovation(innovationRequest);
    }

    @ApiOperation(value = "Получить категории инноваций")
    @GetMapping("/categories")
    public List<CategoryDTO> categories() {
        return innovationService.getAllCategories();
    }

    @ApiOperation(value = "Добавить фото к инновации")
    @PostMapping("/images")
    public Response saveImage(@Valid @RequestBody ImageRequestModel model) {
        innovationService.saveImageByInnovationId(model);
        return new Response();
    }
}
