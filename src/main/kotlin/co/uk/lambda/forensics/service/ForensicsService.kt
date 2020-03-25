package co.uk.lambda.forensics.service

import co.uk.lambda.forensics.config.AppConfig
import co.uk.lambda.forensics.handler.exception.EmailNotValidException
import co.uk.lambda.forensics.handler.exception.MaxLimitException
import co.uk.lambda.forensics.model.Response
import co.uk.lambda.forensics.model.Status
import co.uk.lambda.forensics.model.User
import co.uk.lambda.forensics.repository.UserRepository
import co.uk.lambda.forensics.validator.EmailValidator
import org.springframework.stereotype.Service

@Service
class ForensicsService(val repository: UserRepository, val conf: AppConfig){


    fun getDirection(email: String): Response {

        checkEmail(email)

        val user = repository.findById(email).orElse(User(email))

        val diffX = conf.x.toDouble() - user.guessX
        val diffY = conf.y.toDouble() - user.guessY

       val messageX =  when{
           diffX>0 -> "East"
           diffX<0 -> "West"
           else -> ""
       }

        val messageY = when{
            diffY>0 -> "North"
            diffY<0 -> "South"
            else-> ""
        }

        return if(messageX.isBlank() && messageY.isBlank())
            Response(Status.SUCCESS, "Congratulations")
        else
            Response(Status.FAIL, "Please go $messageX $messageY")
    }

    fun getLocation(email: String, x: Double, y: Double): Response {

        checkEmail(email)

        val user = repository.findById(email).orElse(User(email))

        if(user.counter >= 5)
             throw MaxLimitException("Maximum limit reached")

        user.counter += 1
        user.guessX = x
        user.guessY = y

        repository.save(user)

        return if(conf.x.toDouble() == x && conf.y.toDouble() == y)
            Response(Status.SUCCESS, "Congratulations")
        else
            Response(Status.FAIL, "Please check Direction API")
    }

    private fun checkEmail(email: String){
        if(!EmailValidator.isEmailValid(email))
            throw EmailNotValidException("Email is not valid")
    }
}