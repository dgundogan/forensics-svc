package co.uk.lambda.forensics.validator

import org.junit.Assert
import org.junit.Test


class EmailValidatorTest{

    private val validator = EmailValidator

    @Test
    fun `Given missing @ in email, it returns false`() {
        Assert.assertFalse(validator.isEmailValid("test.com"))
    }

    @Test
    fun `Given missing dot in email, it returns false`() {
        Assert.assertFalse(validator.isEmailValid("test@domain"))
    }

    @Test
    fun `Given missing @ and dot in email, it returns false`() {
        Assert.assertFalse(validator.isEmailValid("domain"))
    }

    @Test
    fun `Given a valid email, it returns true`() {
        Assert.assertTrue(validator.isEmailValid("test@domain.com"))
    }
}