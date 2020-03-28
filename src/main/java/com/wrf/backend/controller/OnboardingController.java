package com.wrf.backend.controller;

import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.OnboardingDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.OnboardingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/onboarding")
@RequiredArgsConstructor
public class OnboardingController {

    final OnboardingService onboardingService;

    @ApiOperation(value = "Добавление онбоардинга")
    @PutMapping("/add")
    public GeneralImageIdModel addOnboarding(@Valid @RequestBody OnboardingModel model) {
        return onboardingService.addOnboarding(model);
    }

    @ApiOperation(value = "Получение онбоардинга")
    @GetMapping("/{type}")
    public List<OnboardingDTO> findOnboarding(@PathVariable String type) {
        return onboardingService.findOnboarding(type);
    }

    @ApiOperation(value = "Получение фото для онбоардинга")
    @GetMapping("/getPhoto/{image_uuid}")
    public String getPhoto(@PathVariable String image_uuid) {
        return onboardingService.getImage(image_uuid);
    }

    @ApiOperation(value = "Проверка статуса сохранения фотографии")
    @GetMapping("/check_image_state/{image_uuid}")
    public Response checkImageState(@PathVariable String image_uuid) {
        onboardingService.checkImageState(image_uuid);
        return new Response();
    }
}
