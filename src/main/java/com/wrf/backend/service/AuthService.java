package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenDTO;
import com.wrf.backend.model.response.UserInfo;
import com.wrf.backend.model.response.UserToken;
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

    private final DbApi dbApi;

    private final HibernateTemplate hibernateTemplate;

    public void checkAccessToken(String token) {
        var userToken = accessTokenMap.get(token);
        TokenUtils.validate(userToken);
        var user = dbApi.getUserByPhone(userToken.getPhone());
        userInfo = new UserInfo(user.getPhone(), user.getId());
    }

    @Transactional
    public TokenDTO login(final LoginModel model) throws NoSuchAlgorithmException {
        var user = Optional.ofNullable(dbApi.getUser(model.getPhone(), model.getPassword()))
                .orElseThrow(() -> new BusinessException(INVALID_LOGIN_DATA));

        user.setLastLoginTime(new Date());
        hibernateTemplate.update(user);
        return createAccessAndRefreshTokens(model.getPhone());
    }

    @Transactional
    public TokenDTO addUser(final UserRegistrationModel model) throws NoSuchAlgorithmException {
        dbApi.checkUserExist(model.getPhone());
        var passwordHash = PasswordUtils.getPasswordHash(model.getPassword());
        hibernateTemplate.save(new User(model.getName(), model.getSurname(),
                model.getPhone(), passwordHash));
        return createAccessAndRefreshTokens(model.getPhone());
    }

    private TokenDTO createAccessAndRefreshTokens(final String phone) {
        var accessToken = UUID.randomUUID().toString();
        accessTokenMap.entrySet().stream()
                .filter(el -> el.getValue().getPhone().equals(phone))
                .forEach(el -> accessTokenMap.remove(el.getKey()));

        accessTokenMap.put(accessToken, new UserToken(phone, TokenUtils.getTokenExpirationTime()));
        return new TokenDTO(accessToken);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
