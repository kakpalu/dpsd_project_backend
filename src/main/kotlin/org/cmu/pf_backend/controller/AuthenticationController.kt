package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.*
import org.cmu.pf_backend.model.Farmer
import org.cmu.pf_backend.service.HashService
import org.cmu.pf_backend.service.TokenService
import org.cmu.pf_backend.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.Currency


@RestController
@RequestMapping("/api")
class AuthenticationController(
    val hashService: HashService,
    val tokenService: TokenService,
    val userService: UserService,
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByEmail(payload.email) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found"
        )

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
            user = UserDto(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,

            )
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.findByEmail(payload.email) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
        }

        val farmer = Farmer(
            email = payload.email,
            password = hashService.hashBcrypt(payload.password),
        )
        val savedUser = userService.createUser(farmer)

        //create Wallet for the created user


        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
            user = UserDto(
                id = savedUser.id,
                firstName = savedUser.firstName,
                lastName = savedUser.lastName,
                email = savedUser.email,

            )
        )
    }
}