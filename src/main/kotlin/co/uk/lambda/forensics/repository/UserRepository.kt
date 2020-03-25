package co.uk.lambda.forensics.repository

import co.uk.lambda.forensics.model.User
import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<User, String> {}