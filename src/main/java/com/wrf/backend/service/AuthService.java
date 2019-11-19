package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenModel;
import com.wrf.backend.model.response.UserToken;
import com.wrf.backend.utils.PasswordUtils;
import com.wrf.backend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequestScope
@Service
public class AuthService {

    private static final Map<String, UserToken> accessTokenMap = new ConcurrentHashMap<>();

    private User userInfo;

    @Autowired
    DbApi dbApi;

    @Autowired
    HibernateTemplate hibernateTemplate;

    public void checkAccessToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Authorization header is empty");
        }
        UserToken userToken = accessTokenMap.get(token);
        TokenUtils.validate(userToken);

        userInfo = dbApi.getUserByPhone(userToken.getPhone());
    }

    public TokenModel login(UserRegistrationModel model) throws NoSuchAlgorithmException {
        User user = dbApi.getUserByPhoneAndPass(model.getPhone(), model.getPassword());
        if (user == null) {
            throw new UnauthorizedException("Неверный логин или пароль");
        }
        return createAccessAndRefreshTokens(model.getPhone());
    }

    public TokenModel addUser(UserRegistrationModel model) throws NoSuchAlgorithmException {
        User user = dbApi.getUserByPhone(model.getPhone());

        if (user != null) {
            throw new BusinessException("Пользователь c номером телефона " + model.getPhone() + " уже существует");
        }
        String passwordHash = PasswordUtils.getPasswordHash(model.getPassword());
        hibernateTemplate.save(new User(model.getPhone(), passwordHash));
        return createAccessAndRefreshTokens(model.getPhone());
    }

    private TokenModel createAccessAndRefreshTokens(String phone) {
        String accessToken = UUID.randomUUID().toString();
        accessTokenMap.entrySet().stream().filter(el ->
                el.getValue().getPhone().equals(phone)).forEach(el ->
                accessTokenMap.remove(el.getKey())
        );
        accessTokenMap.put(accessToken, new UserToken(phone, TokenUtils.getTokenExpirationTime()));
        return new TokenModel(accessToken);
    }

    User getUserInfo() {
        return userInfo;
    }
}
