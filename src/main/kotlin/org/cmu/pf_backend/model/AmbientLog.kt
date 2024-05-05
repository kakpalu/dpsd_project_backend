package org.cmu.pf_backend.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "ambient_log")
data class AmbientLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotNull
    @JoinColumn(name = "fk_ambient_variables")
    @ManyToOne
    val ambientVariable: AmbientVariable = AmbientVariable(),

    val value: Double = 0.0,
    val comment: String = "",

    @CreationTimestamp
    val dateTime: LocalDateTime = LocalDateTime.now()
)