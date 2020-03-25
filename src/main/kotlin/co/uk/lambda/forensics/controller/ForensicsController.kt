package co.uk.lambda.forensics.controller

import co.uk.lambda.forensics.model.Response
import co.uk.lambda.forensics.service.ForensicsService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Slf4j
@RequestMapping("/api")
class ForensicsController(val service : ForensicsService){

    @GetMapping("/{email}/directions")
    fun getDirections(
        @PathVariable("email") email: String
    )
            : ResponseEntity<Response> {

        return ResponseEntity.ok(service.getDirection(email.toLowerCase()))
    }

    @GetMapping("/{email}/location/{x}/{y}")
    fun getLocation(
        @PathVariable("email") email: String,
        @PathVariable("x") x: Double,
        @PathVariable("y") y: Double
    )
            : ResponseEntity<Response> {

        return ResponseEntity.ok(service.getLocation(email.toLowerCase(), x, y))
    }
}