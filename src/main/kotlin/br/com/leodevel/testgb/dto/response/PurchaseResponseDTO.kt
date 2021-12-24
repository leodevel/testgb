package br.com.leodevel.testgb.dto.response

import java.time.LocalDateTime

data class PurchaseResponseDTO(

    var id: String? = null,
    var dealer: DealerResponseDTO? = null,
    var cod: String? = null,
    var value: Double? = null,
    var pctCashback: Double? = null,
    var valueCashback: Double? = null,
    var status: String? = null,
    var dhCreate: LocalDateTime? = null

)