package overcoded.childrenpoints.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET_KEY = "c+wIuydJ9aAiQGy6dx8A6zPsflf7pufB/YN6U5XTG/A="; // TODO: replace with a secure key
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 365;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Date exp = claims.getExpiration();

            String tokenUsername = claims.getSubject();

            if (isTokenExpired(exp))
                return false;
            if (!tokenUsername.equals(userDetails.getUsername()))
                return false;
            return true;
        }  catch(JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(Date exp) {
        return  exp.before(new Date());
    }
}
