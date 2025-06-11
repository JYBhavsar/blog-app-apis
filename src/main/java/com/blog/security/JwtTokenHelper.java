package com.blog.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.Signature;

@Component
public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "jwtTokenKey";

    //Fetch username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Fetch expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //To fetch any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
    	byte[] keyBytes = Decoders.BASE64.decode(secret);
    	SecretKey key =  Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    //Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

    	//Key key = Keys.hmacShaKeyFor(secret.getBytes());
    	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    	
    	return Jwts.builder()
    				.claims(claims)
    				.subject(subject)
    				.issuedAt(new Date(System.currentTimeMillis()))
    				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
    				.signWith(key) // SignatureAlgorithm.HS512
    				.compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
}
