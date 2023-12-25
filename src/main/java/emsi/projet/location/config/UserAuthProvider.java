package emsi.projet.location.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import emsi.projet.location.dto.UserDto;
import emsi.projet.location.entities.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(User user) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000);
		return JWT.create()
				.withIssuer(user.getLogin())
				.withIssuedAt(now)
				.withIssuedAt(validity)
				.withClaim("firstName", user.getFirstName())
				.withClaim("lastName", user.getLastName())
				.sign(Algorithm.HMAC256(secretKey));
	}
	
	public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .login(decoded.getIssuer())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

}
