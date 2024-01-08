package ma.fstt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public void validateToken(final String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

			// Check expiration
			Date expirationDate = claims.getExpiration();
			if (expirationDate != null && expirationDate.before(new Date())) {
				throw new ExpiredJwtException(null, null, "Token has expired", null);
			}
		} catch (ExpiredJwtException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Invalid token", e);
		}
	}

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// expiration is 24 hours
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// get user name from token
	public String getUserNameFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
