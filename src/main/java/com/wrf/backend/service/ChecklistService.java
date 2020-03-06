package com.wrf.backend.service;

import com.wrf.backend.ChecklistItemStatus;
import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.db_api.repository.ChecklistCategoryRepository;
import com.wrf.backend.db_api.repository.ChecklistItemRepository;
import com.wrf.backend.db_api.repository.RoleRepository;
import com.wrf.backend.entity.ChecklistItem;
import com.wrf.backend.mapper.ChecklistCategoryMapper;
import com.wrf.backend.mapper.ChecklistMapper;
import com.wrf.backend.mapper.RoleMapper;
import com.wrf.backend.model.request.ChecklistPackItemStatusModel;
import com.wrf.backend.model.request.ChecklistStatusModel;
import com.wrf.backend.model.response.ChecklistCategoryDTO;
import com.wrf.backend.model.response.ChecklistItemDTO;
import com.wrf.backend.model.response.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final RoleRepository roleRepository;

    private final ChecklistCategoryRepository checklistCategoryRepository;

    private final ChecklistItemRepository checklistItemRepository;

    private final UserDbApi userDbApi;

    public List<RoleDTO> findAllRoles() {
        var roles = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        roles.forEach(role ->
                roleDTOs.add(RoleMapper.INSTANCE.map(role))
        );
        return roleDTOs;
    }

    public List<ChecklistCategoryDTO> findCategoriesByRole(final String roleId) {
        return checklistCategoryRepository
                .findByRoleId(roleId)
                .stream()
                .map(ChecklistCategoryMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<ChecklistItemDTO> findChecklists(final String roleId, final String categoryId) {
        List<ChecklistItem> checklistItems = checklistItemRepository.findByRoleIdAndCategoryId(roleId, categoryId);
        List<ChecklistItemDTO> ChecklistItemDTOs = new ArrayList<>();
        checklistItems.forEach(checklist ->
                ChecklistItemDTOs.add(ChecklistMapper.INSTANCE.map(checklist)));
        return ChecklistItemDTOs;
    }

    public List<RoleDTO> findRolesByUser(final String userId) {
        return userDbApi.findRolesByUser(userId);
    }

    public List<ChecklistItemDTO> findChecklistCategories(@Nullable final String userId,
                                                          @Nullable final String roleId,
                                                          @Nullable final String categoryId) {
        List<ChecklistItem> checklist =
                checklistItemRepository.findByUserIdAndRoleIdAndCategoryId(userId, roleId, categoryId);
        List<ChecklistItemDTO> checklistDTO = new ArrayList<>();
        checklist.forEach(checklistItem ->
                checklistDTO.add(ChecklistMapper.INSTANCE.map(checklistItem)));
        return checklistDTO;
    }

    @Transactional
    public void updateChecklistItemStatus(final String itemId,
                                          final ChecklistStatusModel model) {
        checklistItemRepository.updateChecklistItemStatus(ChecklistItemStatus.valueOf(model.getStatus()), itemId);
    }

    @Transactional
    public void updatePackChecklistStatus(final ChecklistPackItemStatusModel model) {
        model.getItems().forEach(item ->
            checklistItemRepository.updateChecklistItemStatus(ChecklistItemStatus.valueOf(item.getStatus()), item.getItemId()));
    }
}
