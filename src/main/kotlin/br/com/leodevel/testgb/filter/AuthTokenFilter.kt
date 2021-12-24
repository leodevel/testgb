package br.com.leodevel.testgb.filter

import br.com.leodevel.testgb.helper.JwtHelper
import br.com.leodevel.testgb.service.UserDetailsServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter(
    private val userDetailsService: UserDetailsServiceImpl,
    private val jwtHelper: JwtHelper
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {

        val token = getToken(request)

        if (!token.isNullOrEmpty() && jwtHelper.validateJwtToken(token)) {

            val username = jwtHelper.getUsernameFromJwtToken(token)
            val userDetails = userDetailsService.loadUserByUsername(username)

            val authentication = UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.authorities
            )

            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication

        }

        filterChain.doFilter(request, response)

    }

    private fun getToken(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        return if (StringUtils.hasText(headerAuth)
            && headerAuth.startsWith("Bearer ")
        ) headerAuth.substring(7, headerAuth.length) else null
    }

}