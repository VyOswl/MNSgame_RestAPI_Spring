package edu.mns.game.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtils {
    @Value("${secret}")
    private String secret;
    
    public Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetailsMnsGame userDetailsMnsGame){
        String listAuth = userDetailsMnsGame
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(", "));

        Map<String, Object> data = new HashMap<>();
        data.put("id", userDetailsMnsGame.getUser().getId());
        data.put("droits", listAuth);
        data.put("tokenNumber", userDetailsMnsGame.getUser().getNumberToken());

        Calendar dateCreation = Calendar.getInstance();
        long dateCreationInMilliSec = dateCreation.getTimeInMillis();
        Date dateExpiration = new Date((long) (dateCreationInMilliSec * 1.08e+7)/*3hours*/);

        return Jwts.builder()
                .setClaims(data)
                .setExpiration(dateExpiration)
                .setSubject(userDetailsMnsGame.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValidation(String token, UserDetailsMnsGame userDetails){
        Claims claims = getTokenBody(token);
        /*if(claims.getSubject().equals( userDetails.getUsername())){}
        return claims.getSubject().equals(userDetails.getUsername());*/
        boolean userValidation = claims.getSubject()
                .equals(userDetails.getUsername());

        boolean tokenNumberValidation = claims.get("tokenNumber")
                .equals(userDetails.getUser().getNumberToken());

        return userValidation && tokenNumberValidation;
    }

}