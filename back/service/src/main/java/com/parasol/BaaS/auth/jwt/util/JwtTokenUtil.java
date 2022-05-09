package com.parasol.BaaS.auth.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.parasol.BaaS.api_model.AccessToken;
import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static String secretKey;
    private static Integer accessTokenExpirationTime;
    private static Integer reFreshTokenExpirationTime;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ssafy-parasol.com";
    
    @Autowired
	public JwtTokenUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.accesstoken.expiration}") Integer accessTokenExpirationTime,
            @Value("${jwt.refreshtoken.expiration}") Integer reFreshTokenExpirationTime
    ) {
		this.secretKey = secretKey;
		this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.reFreshTokenExpirationTime = reFreshTokenExpirationTime;
	}
    
	public void setExpirationTime() {
    		//JwtTokenUtil.expirationTime = Integer.parseInt(expirationTime);
    		JwtTokenUtil.accessTokenExpirationTime = accessTokenExpirationTime;
            JwtTokenUtil.reFreshTokenExpirationTime = reFreshTokenExpirationTime;
	}

	public static JWTVerifier getVerifier() {
        return JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();
    }
    
    public static AuthToken getToken(String userId) {
        Date accessExpires = JwtTokenUtil.getTokenExpiration(accessTokenExpirationTime);
        Date refreshExpires = JwtTokenUtil.getTokenExpiration(reFreshTokenExpirationTime);
        String accessToken = JWT.create()
                .withSubject(userId)
                .withExpiresAt(accessExpires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));

        String refreshToken = JWT.create()
                .withSubject(userId)
                .withExpiresAt(refreshExpires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));

        return AuthToken.builder()
                .accessToken(new AccessToken(accessToken))
                .refreshToken(new RefreshToken(refreshToken))
                .build();
    }

    public static String getToken(Instant expires, String userId) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(Date.from(expires))
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
    
    public static Date getTokenExpiration(int expirationTime) {
    		Date now = new Date();
    		return new Date(now.getTime() + expirationTime);
    }

    public static String handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();

        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            return null;
        } catch (InvalidClaimException ex) {
            return null;
        } catch (SignatureGenerationException ex) {
            return null;
        } catch (SignatureVerificationException ex) {
            return null;
        } catch (TokenExpiredException ex) {
            return "expired";
        } catch (JWTCreationException ex) {
            return null;
        } catch (JWTDecodeException ex) {
            return null;
        } catch (JWTVerificationException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
        return "success";
    }

    public static void handleError(JWTVerifier verifier, String token) {
        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String getUserId(String token) throws Exception {
        if (token != null) {
            JWTVerifier verifier = JwtTokenUtil.getVerifier();
            String message = JwtTokenUtil.handleError(token);
            if(message == null || "expired".equals(message)) {
                return null;
            }

            DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
            String userId = decodedJWT.getSubject();
            if (userId != null) {
                return userId;
            }
            return null;
        }
        return null;
    }
}
