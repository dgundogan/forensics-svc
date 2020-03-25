package co.uk.lambda.forensics.controller

import co.uk.lambda.forensics.model.Response
import co.uk.lambda.forensics.model.Status
import co.uk.lambda.forensics.service.ForensicsService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.springframework.http.HttpStatus

class ForensicsControllerTest{

    private val service = mockk<ForensicsService>(relaxed = true)
    private val controller = ForensicsController(service)

    @Test
    fun `Given an email , getDirection returns ResponseEntity OK`() {
        val email = "email@domain.com"

        every {
            service.getDirection(email)
        } returns Response(Status.SUCCESS,"OK")

        val response = controller.getDirections(email)
        Assert.assertEquals(HttpStatus.OK, response.statusCode)
        Assert.assertEquals("OK", response.body.message)
    }

    @Test
    fun `Given an email , getLocation returns ResponseEntity OK`() {
        val email = "email@domain.com"

        every {
            service.getLocation(email, 100.10, 200.20)
        } returns Response(Status.SUCCESS, "OK")

        val response = controller.getLocation(email, 100.10, 200.20)
        Assert.assertEquals(HttpStatus.OK, response.statusCode)
        Assert.assertEquals("OK", response.body.message)
    }
}