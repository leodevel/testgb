package br.com.leodevel.testgb.dto.response

data class CashbackResponseDTO(
    var statusCode: Long,
    var body: CashbackBodyResponseDTO
)

data class CashbackBodyResponseDTO(
    var credit: Double
)
