package be.bstorm.technifutur_java23_demospringsecurity.utils;


import be.bstorm.technifutur_java23_demospringsecurity.configs.JwtConfig;
import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtConfig config;

    private final JwtParser parser;

    private final JwtBuilder builder;

    public JwtUtil(JwtConfig config){
        this.config = config;
        SecretKey key = this.config.secretKey;
        this.parser = Jwts.parserBuilder().setSigningKey(key).build();
        this.builder = Jwts.builder().signWith(key);
    }

    public String generateToken(Gamer gamer){

        return builder
                .setSubject(gamer.getUsername())
                .claim("id",gamer.getId())
                .claim("email",gamer.getEmail())
                .claim("role",gamer.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + config.expireAt * 1000L))
                .compact();
    }

    public Claims getClaims(String token){
        return parser.parseClaimsJws(token).getBody();
    }

    public Long getId(String token){
        return getClaims(token).get("id", Long.class);
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public String getEmail(String token){
        return getClaims(token).get("email", String.class);
    }

    public String getRole(String token){
        return getClaims(token).get("role", String.class);
    }

    public boolean isValid(String token){
        Claims claims = getClaims(token);
        Date now = new Date();
        return getRole(token) != null && now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
