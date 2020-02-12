package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.UnauthorizedException;
import com.wrf.backend.model.request.LoginModel;
import com.wrf.backend.model.request.UserRegistrationModel;
import com.wrf.backend.model.response.TokenDTO;
import com.wrf.backend.model.response.UserInfo;
import com.wrf.backend.redis.hash.UserToken;
import com.wrf.backend.redis.repository.RedisUserTokenRepository;
import com.wrf.backend.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequestScope
@RequiredArgsConstructor
public class AuthService {

    private UserInfo userInfo;

    private final DbApi dbApi;

    private final HibernateTemplate hibernateTemplate;

    private final RedisUserTokenRepository redisUserTokenRepository;

    public void checkAccessToken(String token) {
        var userToken = redisUserTokenRepository.findByToken(token);
        Optional.ofNullable(userToken)
                .orElseThrow(UnauthorizedException::new);
        var user = dbApi.getUserByPhone(userToken.getPhone());
        userInfo = new UserInfo(user.getPhone(), user.getId());
    }

    @Transactional
    public TokenDTO login(final LoginModel model) throws NoSuchAlgorithmException {
        var user = Optional.ofNullable(dbApi.getUser(model.getPhone(), model.getPassword()))
                .orElseThrow(() -> new BusinessException(INVALID_LOGIN_DATA));

        user.setLastLoginTime(new Date());
        hibernateTemplate.update(user);
        return createToken(model.getPhone());
    }

    @Transactional
    public TokenDTO addUser(final UserRegistrationModel model,
                            final String deviceToken) throws NoSuchAlgorithmException {
        dbApi.checkUserExist(model.getPhone());
        var passwordHash = PasswordUtils.getPasswordHash(model.getPassword());
        hibernateTemplate.save(new User(model.getName(), model.getSurname(),
                model.getPhone(), passwordHash, deviceToken));
        return createToken(model.getPhone());
    }

    private TokenDTO createToken(final String phone) {
        List<UserToken> userTokens = redisUserTokenRepository.findByPhone(phone);
        if (!userTokens.isEmpty()) {
            redisUserTokenRepository.deleteAll(userTokens);
        }
        var accessToken = UUID.randomUUID().toString();
        var userToken = new UserToken(phone, accessToken);

        redisUserTokenRepository.save(userToken);
        return new TokenDTO(accessToken);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
