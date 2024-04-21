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


data class CreateTransactionDto(
    val name: String,
    val count: Int,
    val note: String?,
)

data class UpdateTransactionDto(
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?,
)