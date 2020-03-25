package co.uk.lambda.forensics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ForensicsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ForensicsApplication>(*args)
        }
    }
}