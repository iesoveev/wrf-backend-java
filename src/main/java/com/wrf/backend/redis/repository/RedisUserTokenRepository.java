package com.wrf.backend.redis.repository;

import com.wrf.backend.redis.hash.UserToken;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface RedisUserTokenRepository extends PagingAndSortingRepository<UserToken, String> {

    UserToken findByToken(String token);

    List<UserToken> findByPhone(String phone);
}
