package com.wrf.backend.controller;

import com.wrf.backend.model.request.ChecklistItemStatusModel;
import com.wrf.backend.model.request.ChecklistPackItemStatusModel;
import com.wrf.backend.model.request.ChecklistStatusModel;
import com.wrf.backend.service.ChecklistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(ChecklistController.class)
public class ChecklistControllerTest extends BaseTest {

    @MockBean
    ChecklistService checklistService;

    private static final String updateStatusPath = "checklists/1/update";
    private static final String updatePackStatusPath = "checklists/update";

    @Test
    public void updateChecklistStatus() throws Exception {
        post(updateStatusPath, new ChecklistStatusModel("done"), isOk);
    }

    @Test
    public void failUpdateChecklistStatus() throws Exception {
        post(updateStatusPath, new ChecklistStatusModel(), isBadRequest);
    }

    @Test
    public void updatePackChecklistStatus() throws Exception {
        var model = new ChecklistPackItemStatusModel(Arrays.asList(
                new ChecklistItemStatusModel(1L, "done"),
                new ChecklistItemStatusModel(2L, "overdue")));
        post(updatePackStatusPath, model, isOk);
    }

    @Test
    public void failUpdatePackChecklistStatus() throws Exception {
        var model = new ChecklistPackItemStatusModel(Collections.emptyList());
        post(updatePackStatusPath, model, isBadRequest);
    }
}
