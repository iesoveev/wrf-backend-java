package com.wrf.backend.redis.hash;

import com.wrf.backend.Description;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@RedisHash("userToken")
public class UserToken {

    @Id
    private String id;

    @Indexed
    private String phone;

    @Indexed
    private String token;

    @TimeToLive(unit = TimeUnit.DAYS)
    @Description("Время жизни токена")
    private Integer ttl;

    public UserToken(String phone, String token) {
        this.phone = phone;
        this.token = token;
        this.ttl = 1;
    }
}
