package co.uk.lambda.forensics.model

data class Response(val status: Status , val message: String)

enum class Status {
    SUCCESS, FAIL
}