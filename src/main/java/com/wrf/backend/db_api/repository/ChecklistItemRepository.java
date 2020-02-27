package com.wrf.backend.db_api.repository;

import com.wrf.backend.ChecklistItemStatus;
import com.wrf.backend.entity.ChecklistItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistItemRepository extends PagingAndSortingRepository<ChecklistItem, String> {

    List<ChecklistItem> findByRoleIdAndCategoryId(String roleId, String categoryId);

    List<ChecklistItem> findByUserIdAndRoleIdAndCategoryId(String userId, String roleId, String categoryId);

    @Modifying
    @Query("update ChecklistItem set status = ?1 where id = ?2")
    void updateChecklistItemStatus(ChecklistItemStatus status, String itemId);
}
