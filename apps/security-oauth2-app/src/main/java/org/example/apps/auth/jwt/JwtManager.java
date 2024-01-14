package org.example.apps.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.example.apps.auth.auth.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * @author rival
 * @since 2023-12-21
 */
@Component
public class JwtManager {
    @Value("${auth.jwt.auth-exp-minutes}")
    private long JWT_AUTH_EXP_MINUTES;

    @Value("${auth.jwt.secret-key}")
    private String JWT_SECRET_KEY;
    @Value("${auth.jwt.issuer}")
    private String JWT_ISSUER;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void init(){
        algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
        jwtVerifier = JWT.require(algorithm).build();
    }

    private Date getExpiryDate(){
        return Date.from(Instant.now().plus(JWT_AUTH_EXP_MINUTES, ChronoUnit.MINUTES));
    }


    public String createToken(JwtUser user){
        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(getExpiryDate())
            .withIssuer(JWT_ISSUER)
            .withIssuedAt(Instant.now())
            .withClaim("auths",authorities)
            .withClaim("id",user.getId())
            .withClaim("type",user.getProvider())
            .withClaim("provider",user.getProvider())
            .sign(algorithm);
    }

    public JwtVerifyResult verifyToken(String token){

        JwtVerifyResult jwtVerifyResult = JwtVerifyResult.builder()
            .verified(false)
            .decoded(false)
            .build();


        try{
            DecodedJWT result = jwtVerifier.verify(token);


            List<String> authorities = result.getClaim("auths").asList(String.class);
            Long id = result.getClaim("id").asLong();

            jwtVerifyResult.setSubject(result.getSubject());
            jwtVerifyResult.setAuthorities(authorities);
            jwtVerifyResult.setId(id);
            jwtVerifyResult.setVerified(true);
            jwtVerifyResult.setDecoded(true);
            jwtVerifyResult.setExpiryDate(result.getExpiresAt());

            return jwtVerifyResult;
        } catch (JWTVerificationException e) {
            try {
                DecodedJWT result = JWT.decode(token);
                jwtVerifyResult.setSubject(result.getSubject());
                jwtVerifyResult.setExpiryDate(result.getExpiresAt());
                jwtVerifyResult.setDecoded(true);
                return jwtVerifyResult;
            } catch (JWTDecodeException ex) {
                return jwtVerifyResult;
            }
        } catch (Exception e){
            return jwtVerifyResult;
        }
    }

}
