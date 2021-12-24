package br.com.leodevel.testgb.helper

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtHelper(
    @Value("\${app.jwtSecret}") private val jwtSecret: String,
    @Value("\${app.jwtExpirationMs}") private val jwtExpirationMs: Long
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetails = authentication.principal as UserDetails
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUsernameFromJwtToken(token: String): String {
        return Jwts
            .parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun validateJwtToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

}