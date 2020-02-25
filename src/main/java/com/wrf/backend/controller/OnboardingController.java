package com.wrf.backend.controller;

import com.wrf.backend.config.AppConfig;
import com.wrf.backend.model.request.*;
import com.wrf.backend.model.response.OnboardingDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.service.OnboardingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/onboarding", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OnboardingController {

    final OnboardingService onboardingService;

    final AppConfig appConfig;

    @ApiOperation(value = "Добавление онбоардинга")
    @PutMapping("/add")
    public GeneralImageIdModel addOnboarding(@RequestBody OnboardingModel model) {
        return onboardingService.addOnboarding(model);
    }

    @ApiOperation(value = "Получение онбоардинга")
    @GetMapping("/{type}")
    public OnboardingDTO findOnboarding(@PathVariable String type) {
        return onboardingService.findOnboarding(type);
    }

    @ApiOperation(value = "Получение фото для онбоардинга")
    @GetMapping("/getPhoto/{image_uuid}")
    public String getPhoto(@PathVariable String image_uuid) {
        return onboardingService.getImage(image_uuid, appConfig.getOnboardingImagePath());
    }

    @ApiOperation(value = "Обновление онбоардинга")
    @PostMapping("/update")
    public Response updateOnboarding(@RequestBody OnboardingUpdateModel model) {
        onboardingService.updateOnboarding(model);
        return new Response();
    }

    @ApiOperation(value = "Проверка статуса сохранения фотографии")
    @GetMapping("/check_image_state/{image_uuid}")
    public Response checkImageState(@PathVariable String image_uuid) {
        onboardingService.checkImageState(image_uuid);
        return new Response();
    }
}
