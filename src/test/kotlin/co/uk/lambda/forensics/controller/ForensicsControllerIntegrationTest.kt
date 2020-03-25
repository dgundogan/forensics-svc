package co.uk.lambda.forensics.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ForensicsControllerIntegrationTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Given invalid email, the direction api returns Bad Request`() {

        mockMvc.perform(get("/api/{email}/directions", "test")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest)

    }

    @Test
    fun `Given valid email, the direction api returns OK`() {

        mockMvc.perform(get("/api/{email}/directions", "test@test.com")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk)

    }

    @Test
    fun `Given invalid email, the location api returns Bad Request`() {

        mockMvc.perform(get("/api/{email}/location/{x}/{y}", "test",100,200)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest)

    }

    @Test
    fun `Given valid email and invalid X coordinate, the location api returns Bad Request`() {

        mockMvc.perform(get("/api/{email}/location/{x}/{y}", "test@test.com","abc",100)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest)

    }

    @Test
    fun `Given valid email and invalid Y coordinate, the location api returns Bad Request`() {

        mockMvc.perform(get("/api/{email}/location/{x}/{y}", "test@test.com",100,"xyz")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest)

    }

    @Test
    fun `Given valid email and invalid coordinates, the location api returns Bad Request`() {

        mockMvc.perform(get("/api/{email}/location/{x}/{y}", "test@test.com","abc","xyz")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest)

    }
}