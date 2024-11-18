package com.akerke.authservice.jwt;

import com.akerke.authservice.user.VotingUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public Boolean isValid (String token, UserDetails userDetails, TokenType tokenType){
        final String userName = extractUserName(token, tokenType);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token, tokenType);
    }

    public TokenPair generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof VotingUserDetails votingUserDetails) {
            claims.put("id", votingUserDetails.votingUser().getId());
            claims.put("role", votingUserDetails.getAuthorities());
//            claims.put("email_verified", ) TODO
        }

        var accessToken = generateToken(claims, userDetails, TokenType.ACCESS_TOKEN);
        var refreshToken = generateToken(claims, userDetails, TokenType.REFRESH_TOKEN);

        TokenPair tokenPair = new TokenPair(accessToken, refreshToken);
        return tokenPair;
    }

    public String extractUserName(String token, TokenType tokenType) {
        return extractClaim(token, Claims::getSubject, tokenType);
    }

    private boolean isTokenExpired(String token, TokenType tokenType) {
        return extractExpiration(token, tokenType).before(new Date());
    }

    private Date extractExpiration(String token, TokenType tokenType) {
        return extractClaim(token, Claims::getExpiration, tokenType);
    }

    private Claims extractAllClaims(String token, TokenType tokenType) {
        return Jwts.parser()
                .setSigningKey(getSigningKey(tokenType)).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey(TokenType tokenType) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getTokens().get(tokenType.name()));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers, TokenType tokenType) {
        final Claims claims = extractAllClaims(token, tokenType);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, TokenType tokenType) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenType.getExpirationAmount()))
                .signWith(getSigningKey(tokenType), SignatureAlgorithm.RS256)
                .compact();
    }

}
