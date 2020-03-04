package com.wrf.backend.db_api.repository;

import com.wrf.backend.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

    Optional<User> findByPhoneAndPassword(String phone, String password);

    Optional<User> findByAccessToken(String token);
}