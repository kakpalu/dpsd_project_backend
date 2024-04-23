package org.cmu.pf_backend.dto

/**
 * This file contains all incoming DTOs.
 * Here, [LoginDto] is a data class containing immutable class members
 */
data class LoginDto(
    val email: String,
    val password: String,
)

data class RegisterDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

data class AddPigDto(
    val name: String,
    val weight: Double,
    val age: Int,
    val breed: String,
)

data class AddAmbientVariableDto(
    val name: String,
    val threshold: Double,
)

data class UpdateTransactionDto(
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?,
)