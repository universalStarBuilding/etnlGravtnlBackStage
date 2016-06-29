package com.etnlgravtnl.common.security;

import com.etnlgravtnl.common.config.Global;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hongtao.
 * Update by  junz
 * 按照HS512 加密规则生产token令牌
 */
public class JwtUtil {

    //单利模式 线程安全保证唯一性
    private  static Key key =null;
    public synchronized  static Key getKeyInstance() {
        if (key == null) {
            key =  MacProvider.generateKey();
        }
        return key;
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param userId 登录用户的id
     * @Description:
     * 生成token 并设置token失效时间
     */
    public static String generateToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuer(Global.getConfig("productName"))
                .setExpiration(getExpiryDate(Integer.valueOf(Global.getConfig("token_expiry"))))
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .setIssuedAt(new Date())
                .compact();
        return token;
    }
    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param auth 认证token串
     * @Description:
     * Replacing "Bearer Token" to "Token" directly
     */
    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        //Replacing "Bearer Token" to "Token" directly
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
    }

    /**
     * @Name:
     * @Author: junz（作者）
     * @Version: V1.00 （版本号）
     * @Create Date: 2016-06-19（创建日期）
     * @param minutes 有效时间 单位为分钟
     * @Description:
     * 获得token失效时间
     */
    private static Date getExpiryDate(int minutes) {

        // 根据当前日期，来得到到期日期
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }

    public static boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getUserId(String jwsToken) {
        if (isValid(jwsToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getSubject();
        }
        return null;
    }
    public static String[] getRoles(String jwsToken) {
        if (isValid(jwsToken))
        {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getAudience().split(",");
        }
        return new String[]{};
    }
    public static int getVersion(String jwsToken) {
        if (isValid(jwsToken)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return Integer.parseInt(claimsJws.getBody().getId());
        }
        return -1;
    }
}
