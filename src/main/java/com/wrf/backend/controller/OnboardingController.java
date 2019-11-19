package com.wrf.backend.controller;

import com.wrf.backend.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @GetMapping("/{type}")
    public Map getOnboardingByType(@PathVariable String type) {
        return onboardingService.getOnboardingByType(type);
    }
}
