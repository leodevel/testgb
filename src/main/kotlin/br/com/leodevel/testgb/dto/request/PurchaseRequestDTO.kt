package br.com.leodevel.testgb.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PurchaseRequestDTO(

    var id: String? = null,

    @field:NotBlank(message = "dealerCpf.mandatory")
    var dealerCpf: String? = null,

    @field:NotBlank(message = "cod.mandatory")
    var cod: String? = null,

    @field:NotNull(message = "value.mandatory")
    var value: Double? = null,

    var dhCreate: LocalDateTime? = null

)