package co.uk.lambda.forensics.service

import co.uk.lambda.forensics.config.AppConfig
import co.uk.lambda.forensics.handler.exception.EmailNotValidException
import co.uk.lambda.forensics.handler.exception.MaxLimitException
import co.uk.lambda.forensics.model.Status
import co.uk.lambda.forensics.model.User
import co.uk.lambda.forensics.repository.UserRepository
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Test
import java.util.*

internal class ForensicsServiceTest{

    private val repository = mockk<UserRepository>()
    private val conf = mockk<AppConfig>()
    private val service = ForensicsService(repository, conf)
    private val email = "test@domain.com"

    @Test(expected = EmailNotValidException::class)
    fun `Given an invalid email , getLocation returns EmailNotValidException`() {

        service.getLocation("test",100.0, 200.0)
    }

    @Test(expected = MaxLimitException::class)
    fun `Given the counter is equal 5 , getLocation returns MaxLimitException`() {

        every{
            repository.findById(any())
        } returns Optional.of(User(email, 5, 10.0, 10.0))

        service.getLocation(email,100.0, 200.0)
    }

    @Test(expected = MaxLimitException::class)
    fun `Given the counter is grater then 5 , getLocation returns MaxLimitException`() {

        every{
            repository.findById(email)
        } returns Optional.of(User(email, 7, 10.0, 10.0))

        service.getLocation(email,100.0, 200.0)

        verify(exactly = 1) { repository.findById(email) }
    }

    @Test
    fun `Given the counter is less then 5 , getLocation returns Fail`() {
        val user = User(email, 2, 10.0, 10.0)
        val savedUser = User(email, 3, 100.0, 200.0)

        every{
            repository.findById(email)
        } returns Optional.of(user)

        every { repository.save(any<User>())  } returns savedUser

        every { conf.x } returns "100.0"
        every { conf.y } returns "100.0"

        val response = service.getLocation(email,100.0, 200.0)
        verify(exactly = 1) {
            repository.findById(email)
            repository.save(any<User>())
        }
        Assert.assertEquals(Status.FAIL, response.status)
    }

    @Test
    fun `Given the correct coordinate , getLocation returns Success`() {

        val user = User(email, 2, 10.0, 10.0)
        val savedUser = User(email, 3, 100.0, 200.0)

        every{
            repository.findById(email)
        } returns Optional.of(user)

        every { repository.save(any<User>())  } returns savedUser

        every { conf.x } returns "100.0"
        every { conf.y } returns "200.0"

        val response = service.getLocation(email,100.0, 200.0)
        verify(exactly = 1) {
            repository.findById(email)
            repository.save(any<User>())
        }
        Assert.assertEquals(Status.SUCCESS, response.status)
    }

    @Test(expected = EmailNotValidException::class)
    fun `Given an invalid email , getDirection returns EmailNotValidException`() {

        service.getDirection("test")
    }

    @Test
    fun `Given an valid email and invalid coordinate , getDirection returns Fail and direction info`() {

        val user = User(email, 2, 10.0, 10.0)

        every{
            repository.findById(email)
        } returns Optional.of(user)

        every { conf.x } returns "100.0"
        every { conf.y } returns "200.0"

        val response = service.getDirection(email)

        Assert.assertEquals(Status.FAIL, response.status)
    }

    @Test
    fun `Given an valid email and valid coordinate , getDirection returns Fail and direction info`() {

        val user = User(email, 2, 10.0, 10.0)

        every{
            repository.findById(email)
        } returns Optional.of(user)

        every { conf.x } returns "10.0"
        every { conf.y } returns "10.0"

        val response = service.getDirection(email)

        Assert.assertEquals(Status.SUCCESS, response.status)
    }
}