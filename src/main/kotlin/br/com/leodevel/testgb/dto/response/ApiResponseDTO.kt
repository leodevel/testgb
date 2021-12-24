package br.com.leodevel.testgb.dto.response

import org.springframework.http.HttpStatus

data class ApiResponseDTO<T> (
    val success: Boolean = true,
    val status: Int = HttpStatus.OK.value(),
    val message: String? = null,
    val data: T? = null
)