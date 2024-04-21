package org.cmu.pf_backend.dto

import org.cmu.pf_backend.model.Farmer
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

data class TransactionDto(
    val id: Long,
    val amount: Double,
    val description: String,
    val reference: String,
    val currency: Currency,
    val recipient: Farmer,
)