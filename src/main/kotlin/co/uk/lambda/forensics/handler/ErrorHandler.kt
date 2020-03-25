package co.uk.lambda.forensics.handler

import co.uk.lambda.forensics.handler.exception.EmailNotValidException
import co.uk.lambda.forensics.handler.exception.MaxLimitException
import co.uk.lambda.forensics.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(EmailNotValidException::class)])
    fun handleEmailException(ex: EmailNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            ex.message.toString()
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(MaxLimitException::class)])
    fun handleMaxLimitException(ex: MaxLimitException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            ex.message.toString()
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(Exception::class)])
    fun handleGeneralException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            ex.message.toString()
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}