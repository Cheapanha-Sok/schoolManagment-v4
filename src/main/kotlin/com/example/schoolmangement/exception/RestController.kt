package com.example.schoolmangement.exception

import com.example.schoolmangement.dto.ResponseNotFoundDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalTime

@RestControllerAdvice
class RestController {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundHandler(ex : NotFoundException) :ResponseEntity<ResponseNotFoundDto>{
        val errorMessage = ResponseNotFoundDto(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            localTime = LocalTime.now()
        )
        return ResponseEntity(errorMessage , HttpStatus.NOT_FOUND)
    }
}