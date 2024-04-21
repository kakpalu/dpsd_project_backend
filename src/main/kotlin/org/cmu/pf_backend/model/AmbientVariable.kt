package org.cmu.pf_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "ambient_variable")
data class AmbientVariable (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var treshold : Double = 0.0,
)