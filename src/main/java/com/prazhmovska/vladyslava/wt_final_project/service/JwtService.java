package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service for handling JWT (JSON Web Token) operations such as token generation, validation, and extraction.
 *
 * <p>This service provides methods to generate JWT tokens, validate tokens, and extract claims such as the
 * user's email or expiration date from a given token. It uses a secret key defined in the application properties
 * for signing and verifying the tokens.</p>
 */
@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Extracts the user's email from the given JWT token.
     *
     * @param token The JWT token from which to extract the email.
     * @return The user's email extracted from the token.
     */
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generates a JWT token based on the provided user details.
     *
     * <p>The generated token will include the user's ID and email in the claims.</p>
     *
     * @param userDetails The {@link UserDetails} object containing the user's information.
     * @return A JWT token as a {@link String} containing the user's claims.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User) {
            claims.put("id", ((User) userDetails).getId());
        }
        claims.put("email", userDetails.getUsername());
        return generateToken(claims, userDetails);
    }

    /**
     * Validates the JWT token by comparing the username in the token with the provided user details
     * and checking if the token has expired.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The {@link UserDetails} to validate against the token.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserEmail(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Extracts a specific claim from the JWT token using the provided claims resolver function.
     *
     * @param token           The JWT token from which to extract the claim.
     * @param claimsResolvers A function that extracts the desired claim from the token.
     * @param <T>             The type of the extracted claim.
     * @return The extracted claim of type {@link T}.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Generates a JWT token with the specified additional claims and user details.
     *
     * <p>This method creates the JWT token by setting claims such as the subject (username), issued date,
     * and expiration date, and signs the token using the secret key.</p>
     *
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The {@link UserDetails} object containing the user's information.
     * @return The generated JWT token as a {@link String}.
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 12))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if the JWT token has expired.
     *
     * @param token The JWT token to check for expiration.
     * @return {@code true} if the token is expired, {@code false} otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date of the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token from which to extract all claims.
     * @return A {@link Claims} object containing all claims in the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used to sign and verify JWT tokens.
     *
     * <p>The signing key is derived from the secret key defined in the application properties.</p>
     *
     * @return The signing key used for signing and verifying JWT tokens.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

