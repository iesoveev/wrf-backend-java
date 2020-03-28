package com.wrf.backend.controller;

import com.wrf.backend.model.request.ChecklistPackItemStatusModel;
import com.wrf.backend.model.request.ChecklistStatusModel;
import com.wrf.backend.model.response.ChecklistCategoryDTO;
import com.wrf.backend.model.response.ChecklistItemDTO;
import com.wrf.backend.model.response.Response;
import com.wrf.backend.model.response.RoleDTO;
import com.wrf.backend.service.ChecklistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    @GetMapping("/categories/{role_id}")
    public List<ChecklistCategoryDTO> findAllCategories(@PathVariable Long role_id) {
        return checklistService.findCategoriesByRole(role_id);
    }

    @ApiOperation(value = "Получение чек-листов")
    @GetMapping
    public List<ChecklistItemDTO> checklists(@RequestParam Long role_id,
                                             @RequestParam Long category_id) {
        return checklistService.findChecklists(role_id, category_id);
    }

    @ApiOperation(value = "Получение ролей для конкретного пользователя")
    @GetMapping("/user/{user_id}/roles")
    public List<RoleDTO> findRolesByUser(@PathVariable Long user_id) {
        return checklistService.findRolesByUser(user_id);
    }

    @ApiOperation(value = "Получение чек-листов для пользователя")
    @GetMapping("/user/{user_id}/roles/{role_id}/categories/{category_id}")
    public List<ChecklistItemDTO> findChecklistCategories(@PathVariable Long user_id,
                                                          @PathVariable Long role_id,
                                                          @PathVariable Long category_id) {
        return checklistService.findChecklistCategories(user_id, role_id, category_id);
    }

    @ApiOperation(value = "Обновление статуса таски чек-листа")
    @PostMapping("/{item_id}/update")
    public Response updateChecklistStatus(@PathVariable Long item_id,
                                         @Valid @RequestBody ChecklistStatusModel model) {
        checklistService.updateChecklistItemStatus(item_id, model);
        return new Response();
    }

    @ApiOperation(value = "Обновление статуса тасок чек-листа (пачкой)")
    @PostMapping("/update")
    public Response updatePackChecklistStatus(@Valid @RequestBody ChecklistPackItemStatusModel model) {
        checklistService.updatePackChecklistStatus(model);
        return new Response();
    }

}

