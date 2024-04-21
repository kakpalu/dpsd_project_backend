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
    fun getUserProfile(authentication: Authentication): ResponseEntity<UserDto> {
        val authUser = authentication.toUser()
        val userDto = UserDto(
            id = authUser.id,
            firstName = authUser.firstName,
            lastName = authUser.lastName,
            email = authUser.email,

        )
        return ResponseEntity(userDto, HttpStatus.OK)
    }

    @PutMapping("/user/profile")
    fun updateUserProfile(authentication: Authentication, @RequestBody farmer: Farmer): ResponseEntity<Farmer> {

        val authUser = authentication.toUser()
        val updatedUser = authUser.copy(firstName = farmer.firstName, lastName = farmer.lastName, email = farmer.email)
        farmerService.updateUser(
            id = updatedUser.id,
            firstName =  updatedUser.firstName,
            lastName =  updatedUser.lastName,
            email =  updatedUser.email
        )
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}