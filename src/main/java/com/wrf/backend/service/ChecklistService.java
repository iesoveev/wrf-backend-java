package com.wrf.backend.service;

import com.wrf.backend.ChecklistItemStatus;
import com.wrf.backend.db_api.repository.ChecklistItemRepository;
import com.wrf.backend.db_api.repository.RoleRepository;
import com.wrf.backend.entity.BaseEntity;
import com.wrf.backend.entity.ChecklistItem;
import com.wrf.backend.entity.Role;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.mapper.ChecklistCategoryMapper;
import com.wrf.backend.mapper.ChecklistMapper;
import com.wrf.backend.mapper.RoleMapper;
import com.wrf.backend.model.request.ChecklistPackItemStatusModel;
import com.wrf.backend.model.request.ChecklistStatusModel;
import com.wrf.backend.model.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final RoleRepository roleRepository;

    private final ChecklistItemRepository checklistItemRepository;

    private final AuthService authService;

    public List<RoleDTO> findAllRoles() {
        var roles = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        roles.forEach(role ->
                roleDTOs.add(RoleMapper.INSTANCE.map(role))
        );
        return roleDTOs;
    }

    public List<ChecklistCategoryDTO> findCategoriesByRole(final Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessException(ROLE_IS_NOT_FOUND));
        return map(role.getCategories().stream(),
                ChecklistCategoryMapper.INSTANCE::map);
    }

    public List<ChecklistItemDTO> findChecklists(final Long roleId, final Long categoryId) {
        Stream<ChecklistItem> checklistStream = checklistItemRepository
                .findByUserIdAndRoleIdAndCategoryId(authService.getUserInfo().getId(), roleId, categoryId).stream();
        return map(checklistStream, ChecklistMapper.INSTANCE::map);

    }

    @Transactional
    public void updateChecklistItemStatus(final Long itemId,
                                          final ChecklistStatusModel model) {
        checklistItemRepository.updateChecklistItemStatus(ChecklistItemStatus.valueOf(model.getStatus().toUpperCase()), itemId);
    }

    @Transactional
    public void updatePackChecklistStatus(final ChecklistPackItemStatusModel model) {
        model.getItems().forEach(item ->
            checklistItemRepository.updateChecklistItem(ChecklistItemStatus.valueOf(item.getStatus()), item.getItemId()));
    }

    private <T extends IdDTO, E extends BaseEntity> List<T> map (
            Stream<E> stream, Function<E, T> mapper) {
        return stream
                .map(mapper)
                .collect(Collectors.toUnmodifiableList());
    }
}
