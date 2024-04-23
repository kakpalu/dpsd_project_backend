package org.cmu.pf_backend.controller

import org.cmu.pf_backend.config.toUser
import org.cmu.pf_backend.dto.UserDto
import org.cmu.pf_backend.model.Farmer
import org.cmu.pf_backend.service.FarmerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/api")
class UserController (val farmerService: FarmerService) {

    @GetMapping("/user/profile")
    fun getUserProfile(@RequestHeader("Authorization") bearerToken: String): ResponseEntity<UserDto> {
        val authUser = farmerService.findByToken(bearerToken) ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)
        val userDto = UserDto(
            id = authUser.id,
            firstName = authUser.firstName,
            lastName = authUser.lastName,
            email = authUser.email,
        )
        return ResponseEntity(userDto, HttpStatus.OK)
    }

    @PutMapping("/user/profile")
    fun updateUserProfile(@RequestHeader("Authorization") bearerToken: String, @RequestBody farmer: Farmer): ResponseEntity<UserDto> {
        val authUser = farmerService.findByToken(bearerToken) ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)
        val updatedUser = authUser.copy(firstName = farmer.firstName, lastName = farmer.lastName, email = farmer.email)
        farmerService.updateUser(
            id = updatedUser.id,
            firstName =  updatedUser.firstName,
            lastName =  updatedUser.lastName,
            email =  updatedUser.email
        )
        val userDto = UserDto(
            id = updatedUser.id,
            firstName = updatedUser.firstName,
            lastName = updatedUser.lastName,
            email = updatedUser.email,

            )
        return ResponseEntity(userDto, HttpStatus.OK)
    }
}