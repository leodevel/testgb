package br.com.leodevel.testgb.controller

import br.com.leodevel.testgb.dto.request.LoginRequestDTO
import br.com.leodevel.testgb.dto.response.ApiResponseDTO
import br.com.leodevel.testgb.dto.response.LoginResponseDTO
import br.com.leodevel.testgb.helper.JwtHelper
import br.com.leodevel.testgb.helper.removeCpfFormatting
import br.com.leodevel.testgb.model.Dealer
import br.com.leodevel.testgb.model.toResponseDTO
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Api(tags = ["Login"], description = "Login")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtHelper: JwtHelper
) {

    @PostMapping("/login")
    @ApiOperation("Login")
    fun login(@Valid @RequestBody body: LoginRequestDTO): ApiResponseDTO<LoginResponseDTO> {

        body.cpf = body.cpf.removeCpfFormatting()

        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(body.cpf, body.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val token = jwtHelper.generateJwtToken(authentication)
        val dealer = authentication.principal as Dealer

        return ApiResponseDTO(
            data = LoginResponseDTO(
                dealer = dealer.toResponseDTO(),
                token = token
            )
        )

    }

}