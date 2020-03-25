package co.uk.lambda.forensics.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "location")
class AppConfig {
    lateinit var x: String
    lateinit var y: String
}