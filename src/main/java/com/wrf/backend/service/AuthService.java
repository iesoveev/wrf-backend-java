package com.wrf.backend.service;

import com.wrf.backend.db_api.UserDbApi;
import com.wrf.backend.db_api.repository.UserRepository;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenDTO;
import com.wrf.backend.model.response.UserInfo;
import com.wrf.backend.redis.hash.UserToken;
//import com.wrf.backend.redis.repository.RedisUserTokenRepository;
import com.wrf.backend.utils.PasswordUtils;
import com.wrf.backend.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequestScope
@RequiredArgsConstructor
public class AuthService {

    private static final Map<String, UserToken> accessTokenMap = new ConcurrentHashMap<>();

    private UserInfo userInfo;

    private final UserDbApi userDbApi;

    private final HibernateTemplate hibernateTemplate;

    private final UserRepository userRepository;

    public void checkAccessToken(final String token) {
        final var userToken = accessTokenMap.get(token);
        Optional.ofNullable(userToken)
                .orElseThrow(UnauthorizedException::new);
        final var user = userDbApi.getUserByPhone(userToken.getPhone());
        userInfo = new UserInfo(user.getPhone(), user.getId());
    }

    @Transactional
    public TokenDTO login(final LoginModel model) throws NoSuchAlgorithmException {
        final var user = userRepository.findByPhoneAndPassword(model.getPhone(),
                PasswordUtils.getPasswordHash(model.getPassword()))
                .orElseThrow(() -> new BusinessException(INVALID_LOGIN_DATA));

        user.setLastLoginTime(new Date());
        userRepository.save(user);
        return createToken(model.getPhone());
    }

    @Transactional
    public TokenDTO addUser(final UserRegistrationModel model,
                            final String deviceToken) throws NoSuchAlgorithmException {
        userDbApi.checkUserExist(model.getPhone());
        final String passwordHash = PasswordUtils.getPasswordHash(model.getPassword());
        hibernateTemplate.save(new User(model.getName(), model.getSurname(),
                model.getPhone(), passwordHash, deviceToken));
        return createToken(model.getPhone());
    }

    private TokenDTO createToken(final String phone) {
        var accessToken = UUID.randomUUID().toString();
        accessTokenMap
                .entrySet()
                .stream()
                .filter(el -> el.getValue().getPhone().equals(phone))
                .forEach(el -> accessTokenMap.remove(el.getKey()));

        accessTokenMap.put(accessToken, new UserToken(phone, accessToken));
        return new TokenDTO(accessToken);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
