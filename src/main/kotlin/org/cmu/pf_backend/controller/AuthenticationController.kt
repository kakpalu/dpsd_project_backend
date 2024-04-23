package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.*
import org.cmu.pf_backend.model.Farmer
import org.cmu.pf_backend.service.HashService
import org.cmu.pf_backend.service.TokenService
import org.cmu.pf_backend.service.FarmerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api")
class AuthenticationController(
    val hashService: HashService,
    val tokenService: TokenService,
    val farmerService: FarmerService,
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): ResponseEntity<LoginResponseDto> {
        val user = farmerService.findByEmail(payload.email) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found"
        )

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed")
        }

        return ResponseEntity( LoginResponseDto(
            token = tokenService.createToken(user),
            user = UserDto(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
            )
        ), HttpStatus.OK
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): ResponseEntity<LoginResponseDto> {
        if (farmerService.findByEmail(payload.email) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
        }

        val farmer = Farmer(
            email = payload.email,
            firstName = payload.firstName,
            lastName = payload.lastName,
            password = hashService.hashBcrypt(payload.password),
        )
        val savedUser = farmerService.createUser(farmer)

        //create Wallet for the created user



        return ResponseEntity(LoginResponseDto(
            token = tokenService.createToken(savedUser),
            user = UserDto(
                id = savedUser.id,
                firstName = savedUser.firstName,
                lastName = savedUser.lastName,
                email = savedUser.email,
            )),HttpStatus.OK
        )
    }
    @Async
    @GetMapping("/health")
    fun health(): String {
        return "OK"
    }
}