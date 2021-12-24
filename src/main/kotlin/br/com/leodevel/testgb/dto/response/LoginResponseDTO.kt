package br.com.leodevel.testgb.dto.response

data class LoginResponseDTO(
    var dealer: DealerResponseDTO,
    var token: String
)