package org.cmu.pf_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "pig")
data class Pig (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var weight: Double = 0.0,
    var age: Int = 0,
    var breed: String = ""
)