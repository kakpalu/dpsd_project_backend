package org.cmu.pf_backend.service

import org.cmu.pf_backend.model.Farmer
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * This service creates and parses tokens.
 * It will do database operations.
 */
@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val farmerService: FarmerService
) {
    fun createToken(farmer: Farmer): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(farmer.firstName)
            .claim("userId", farmer.id)
            .build()

        val token = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
        storeToken(token, farmer)
        return token
    }

    fun parseToken(token: String): Farmer? {
        return try {
            farmerService.findByToken(token)
        } catch (e: Exception) {
            null
        }
    }

    fun storeToken(token: String, farmer: Farmer) {
        farmerService.storeToken(token, farmer)
    }
}