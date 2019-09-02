package com.dong.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.dong.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-25
 **/
@Component
public class JwtUtil {

    private static final String TOKEN_KEY = "1cdaef38fd155247702158cb70f8e4f8";//私钥

    /**
     * 创建token
     * */
    public static String createToken(User user){
        return JWT.create()
                .withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(TOKEN_KEY));
    }

    /**
     * 校验token
     * */
    public static void verify(String token,User user){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build();
        jwtVerifier.verify(token);
    }

}
