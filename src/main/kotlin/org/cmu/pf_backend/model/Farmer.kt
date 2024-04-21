package org.cmu.pf_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "farmer")
data class Farmer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var token: String = ""
)


