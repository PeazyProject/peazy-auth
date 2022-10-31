package com.peazy.auth.configuration;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.stereotype.Component;

import com.peazy.auth.model.args.JwtRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil implements Serializable{
    
    private static final long EXPIRATION_TIME = 1 * 60 * 1000;

	private static final String CLAIM_NAME_USEREMAIL = "userEmail";
	private static final String CLAIM_NAME_PASSWORD = "password";

    /**
     * JWT SECRET KEY
     */
    private static final String SECRET = "peazy secret";

    /**
     * 簽發JWT
     */
    public String generateToken(HashMap<String, String> userDetails, JwtRequest jwtRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_NAME_USEREMAIL, userDetails.get("userEmail"));
        claims.put(CLAIM_NAME_PASSWORD, userDetails.get("password"));
        
        // userDetails.getUsername()
        String tmpUserName = "Jay";
        return doGenerateToken(claims, tmpUserName);
    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public void validateToken(String token) throws AuthException {
        try {
            Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid.");
        }
    }















    
}
