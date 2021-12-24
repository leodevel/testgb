package br.com.leodevel.testgb.dto.request

import javax.validation.constraints.NotBlank

data class UpdatePasswordRequestDTO(

    @field:NotBlank(message = "cpf.mandatory")
    var cpf: String? = null,

    @field:NotBlank(message = "password.mandatory")
    var password: String? = null

)