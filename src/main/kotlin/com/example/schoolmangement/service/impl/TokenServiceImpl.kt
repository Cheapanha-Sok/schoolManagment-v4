package com.example.schoolmangement.service.impl

import com.example.schoolmangement.configuration.JwtProperties
import com.example.schoolmangement.service.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
@Service
class TokenServiceImpl(private val jwtProperties: JwtProperties) : TokenService {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )
    override fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
    ):
            String = Jwts.builder()
        .claims()
        .subject(userDetails.username)
        .issuedAt(Date(System.currentTimeMillis()))
        .expiration(expirationDate)
        .add(additionalClaims)
        .and()
        .signWith(secretKey)
        .compact()

    override fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser.parseSignedClaims(token).payload
    }

    override fun extractEmail(token: String?): String = getAllClaims(token!!).subject

    override fun isExpired(token: String): Boolean =
        getAllClaims(token).expiration.before(Date(System.currentTimeMillis()))

    override fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }
}