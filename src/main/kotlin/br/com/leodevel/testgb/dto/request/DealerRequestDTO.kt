package br.com.leodevel.testgb.dto.request

import javax.validation.constraints.NotBlank

data class DealerRequestDTO(

    var id: String? = null,

    @field:NotBlank(message = "name.mandatory")
    var name: String? = null,

    @field:NotBlank(message = "cpf.mandatory")
    var cpf: String? = null,

    @field:NotBlank(message = "email.mandatory")
    var email: String? = null,

    @field:NotBlank(message = "password.mandatory")
    var password: String? = null

)