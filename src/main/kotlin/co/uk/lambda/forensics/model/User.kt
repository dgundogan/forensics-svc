package co.uk.lambda.forensics.model

import lombok.AllArgsConstructor
import javax.persistence.*

@Entity
@AllArgsConstructor
class User() {
    @Id
    var email: String = ""

    var counter: Int = 0

    var guessX: Double = 0.0

    var guessY: Double = 0.0

    constructor(email: String): this(){
        this.email = email
    }

    constructor(email: String, counter: Int, guessX: Double, guessY: Double): this(){
        this.email = email
        this.counter = counter
        this.guessX = guessX
        this.guessY = guessY
    }
}