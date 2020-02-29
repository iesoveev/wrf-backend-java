package com.wrf.backend.controller;

import com.wrf.backend.model.request.ChecklistPackItemStatusModel;
import com.wrf.backend.model.request.ChecklistStatusModel;
import com.wrf.backend.model.response.ChecklistCategoryDTO;
import com.wrf.backend.model.response.ChecklistItemDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.model.response.RoleDTO;
import com.wrf.backend.service.ChecklistService;
import com.wrf.backend.utils.ValidationUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/checklists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChecklistController {

    final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @ApiOperation(value = "Получние ролей пользователей")
    @GetMapping("/roles")
    public List<RoleDTO> findAllRoles() {
        return checklistService.findAllRoles();
    }

    @ApiOperation(value = "Получение категорий чек-листов")
    @GetMapping("/categories")
    public List<ChecklistCategoryDTO> findAllCategories() {
        return checklistService.findAllCategories();
    }

    @ApiOperation(value = "Получение чек-листов")
    @GetMapping
    public List<ChecklistItemDTO> checklists(@RequestParam String role_id,
                                             @RequestParam String category_id) {
        return checklistService.findChecklists(role_id, category_id);
    }

    @ApiOperation(value = "Получение ролей для конкретного пользователя")
    @GetMapping("/user/{user_id}/roles")
    public List<RoleDTO> findRolesByUser(@PathVariable String user_id) {
        return checklistService.findRolesByUser(user_id);
    }

    @ApiOperation(value = "Получение чек-листов для пользователя")
    @GetMapping("/user/{user_id}/roles/{role_id}/categories/{category_id}")
    public List<ChecklistItemDTO> findChecklistCategories(@PathVariable String user_id,
                                                          @PathVariable String role_id,
                                                          @PathVariable String category_id) {
        return checklistService.findChecklistCategories(user_id, role_id, category_id);
    }

    @ApiOperation(value = "Получение чек-листов для пользователя")
    @PostMapping("/{item_id}/update")
    public Response updateChecklistStatus(@PathVariable String item_id,
                                         @RequestBody ChecklistStatusModel model) {
        checklistService.updateChecklistItemStatus(item_id, model);
        return new Response();
    }

    @ApiOperation(value = "Получение чек-листов для пользователя")
    @PostMapping("/update")
    public Response updatePackChecklistStatus(@RequestBody ChecklistPackItemStatusModel model)
            throws IllegalAccessException {
        ValidationUtils.validate(model);
        checklistService.updatePackChecklistStatus(model);
        return new Response();
    }

}

