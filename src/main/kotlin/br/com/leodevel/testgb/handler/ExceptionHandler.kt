package br.com.leodevel.testgb.handler

import br.com.leodevel.testgb.dto.response.ApiResponseDTO
import br.com.leodevel.testgb.dto.response.FieldValidationErrorResponseDTO
import br.com.leodevel.testgb.exception.EntityNotFoundException
import br.com.leodevel.testgb.exception.LoginFailedException
import br.com.leodevel.testgb.exception.UserNotFoundException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ExceptionHandler(private val messageSource: MessageSource) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ApiResponseDTO<Any>{
        return ApiResponseDTO(
            success = false,
            status = HttpStatus.BAD_REQUEST.value(),
            message = getMessageByProperty(ex.message)
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handleUserNotFoundException(ex: UserNotFoundException): ApiResponseDTO<Any>{
        return ApiResponseDTO(
            success = false,
            status = HttpStatus.BAD_REQUEST.value(),
            message = getMessageByProperty("user.not.found")
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [LoginFailedException::class])
    fun handleLoginFailedException(ex: LoginFailedException): ApiResponseDTO<Any>{
        return ApiResponseDTO(
            success = false,
            status = HttpStatus.BAD_REQUEST.value(),
            message = getMessageByProperty(ex.message?:"login.failed")
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ApiResponseDTO<List<FieldValidationErrorResponseDTO>> {
        val errors = ex.bindingResult.allErrors.map { error: ObjectError ->
            val field = (error as FieldError).field
            val error = getMessageByProperty(error.defaultMessage)
            FieldValidationErrorResponseDTO(field, error)
        }
        return ApiResponseDTO(
            success = false,
            status = HttpStatus.BAD_REQUEST.value(),
            message = getMessageByProperty("fields.mandatory"),
            data = errors
        )
    }

    fun getMessageByProperty(property: String?) =
        messageSource.getMessage(
            property ?: "",
            null,
            property ?: "server.error",
            Locale.getDefault()
        )

}