package com.wrf.backend.db_api.repository;

import com.wrf.backend.ChecklistItemStatus;
import com.wrf.backend.entity.ChecklistItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistItemRepository extends PagingAndSortingRepository<ChecklistItem, Long> {

    List<ChecklistItem> findByUserIdAndRoleIdAndCategoryId(Long userId, Long roleId, Long categoryId);

    @Modifying
    @Query("update ChecklistItem set status = ?1 where id = ?2")
    void updateChecklistItemStatus(ChecklistItemStatus status, Long itemId);

    @Modifying
    @Query("update ChecklistItem set status = ?1 where id = ?2 and status = 'INTACT'")
    void updateChecklistItem(ChecklistItemStatus status, Long itemId);
}
