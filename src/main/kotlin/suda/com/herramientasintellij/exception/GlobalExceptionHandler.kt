package suda.com.herramientasintellij.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String,
    val details: Map<String, String> = emptyMap(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> =
        build(HttpStatus.NOT_FOUND, ex.message ?: "Recurso no encontrado")

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val details = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "inválido") }
        return build(HttpStatus.BAD_REQUEST, "Datos inválidos", details)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleUnreadable(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> =
        build(HttpStatus.BAD_REQUEST, "El JSON está mal formado o tiene un valor no permitido")

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleConflict(ex: DataIntegrityViolationException): ResponseEntity<ErrorResponse> =
        build(HttpStatus.CONFLICT, "Dato duplicado o que viola una regla de la base de datos")

    private fun build(
        status: HttpStatus,
        message: String,
        details: Map<String, String> = emptyMap(),
    ): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(status).body(ErrorResponse(status.value(), status.reasonPhrase, message, details))
}