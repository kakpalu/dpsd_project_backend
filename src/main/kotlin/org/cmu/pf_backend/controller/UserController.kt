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

    @GetMapping("/users")
    // the function is used to get all users in the database. It returns a list of UserDto, but it does not require any input.
    fun getUsers(): ResponseEntity<List<UserDto>> {
        val users = farmerService.getAllUsers()
        val userDtos = users.map {
            UserDto(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
            )
        }
        return ResponseEntity(userDtos, HttpStatus.OK)
    }


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

data class PasswordChangeRequest(val password: String)

    @PutMapping("/user/password")
    fun changePassword(@RequestHeader("Authorization") bearerToken: String, @RequestBody passwordChangeRequest: PasswordChangeRequest): ResponseEntity<UserDto> {
        val authUser = farmerService.findByToken(bearerToken) ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)
        farmerService.changePassword(authUser.id, passwordChangeRequest.password)
        return ResponseEntity(HttpStatus.OK)
    }

    
    data class ResetPasswordRequest(val email: String, val password: String)

    // function to reset the password
    @PutMapping("/user/reset-password")
    fun resetPassword(@RequestBody request: ResetPasswordRequest): ResponseEntity<Void> {
        farmerService.resetPassword(request.email, request.password)
        return ResponseEntity(HttpStatus.OK)
    }


    // function to delete the user account
    @DeleteMapping("/user/profile")
    fun deleteUserProfile(@RequestHeader("Authorization") bearerToken: String): ResponseEntity<UserDto> {
        val authUser = farmerService.findByToken(bearerToken) ?: return ResponseEntity(HttpStatus.UNAUTHORIZED)
        farmerService.deleteUser(authUser.id)
        return ResponseEntity(HttpStatus.OK)
    }
}