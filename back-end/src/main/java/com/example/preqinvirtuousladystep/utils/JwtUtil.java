package com.example.preqinvirtuousladystep.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.key}")
    private String KEY;

    private String token;

    // 接收业务数据,生成token并返回
    public String getToken(Map<String, Object> claims) {
        token = JWT.create().withClaim("claims", claims).withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60)).sign(Algorithm.HMAC256(KEY));
        return token;
    }

    // 接收token,验证token,并返回业务数据
    public Map<String, Object> parseToken(String token) throws JWTDecodeException {
        return JWT.require(Algorithm.HMAC256(KEY)).build().verify(token).getClaim("claims").asMap();
    }

    public String getThisToken(){
        return token;
    }

}
