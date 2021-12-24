package br.com.leodevel.testgb.dto.response

data class FieldValidationErrorResponseDTO(
    var field: String,
    var error: String? = null
)
