package org.cmu.pf_backend.dto

import java.util.*

/**
 * This file contains all outgoing DTOs.
 *
 */

data class LoginResponseDto(
    val token: String,
    val user: UserDto
)


data class WalletDto(
    val id: Long,
    val balance: Double,
    val currency: Currency,
)
data class UserDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
)

data class PigDto(
    val id: Long,
    val name: String,
    val weight: Double,
    val age: Int,
    val breed: String,
)

data class AmbientVariableResponseDto(
    val id: Long,
    val name: String,
    val threshold: Double,
)