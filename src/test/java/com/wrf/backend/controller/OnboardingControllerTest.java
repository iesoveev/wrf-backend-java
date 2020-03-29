package com.wrf.backend.controller;

import com.wrf.backend.model.request.OnboardingModel;
import com.wrf.backend.service.OnboardingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(OnboardingController.class)
public class OnboardingControllerTest extends BaseTest {

    @MockBean
    OnboardingService onboardingService;

    @Test
    public void addOnboardingTest() throws Exception {
        var model = new OnboardingModel("ASD", "qwert",
                "qwerty", "qwerty");
        put("onboarding/add", model, isOk);

    }

    @Test
    public void failAddOnboardingTest() throws Exception {
        var model = new OnboardingModel(null, null,
                "qwerty", "qwerty");
        put("onboarding/add", model, isBadRequest);

    }


}
