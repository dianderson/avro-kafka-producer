package br.com.handaltech.avrokafkaproducer.common

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant


@ControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorHandlerModel> {
        val errors = ex.constraintViolations
            .map {
                ErrorMessage(
                    field = it.propertyPath.toString(),
                    error = it.message
                )
            }.toSet()

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorHandlerModel(
                    timestamp = Instant.now(),
                    httpStatus = HttpStatus.BAD_REQUEST.value(),
                    errors = errors,
                    path = request.servletPath
                )
            )
    }
}