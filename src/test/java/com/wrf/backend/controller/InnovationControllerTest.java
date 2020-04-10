package com.wrf.backend.controller;

import com.wrf.backend.model.request.ImageRequestModel;
import com.wrf.backend.model.request.UserInnovationRequest;
import com.wrf.backend.service.InnovationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(InnovationController.class)
public class InnovationControllerTest extends BaseTest {

    private static final String innovationsPath = "innovations";
    private static final String saveImagePath = "innovations/images";

    @MockBean
    InnovationService innovationService;
//
//    @Test
//    public void addInnovationTest() throws Exception {
//        var model = new UserInnovationRequest(1L, "123456");
//
//        post(innovationsPath, model, isOk);
//    }
//
//    @Test
//    public void failAddInnovationTest() throws Exception {
//        var model = new UserInnovationRequest(null, null);
//        post(innovationsPath, model, isBadRequest);
//    }

    @Test
    public void saveImage() throws Exception {
        var model = new ImageRequestModel("wrueghfbuehdsbjkn", 1L);
        post(saveImagePath, model, isOk);
    }

    @Test
    public void failSaveImage() throws Exception {
        var model = new ImageRequestModel("wrueghfbuehdsbjkn", null);
        post(saveImagePath, model, isBadRequest);
    }
}
