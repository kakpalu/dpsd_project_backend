package org.cmu.pf_backend.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "pig_vitals")
data class PigVitals(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @NotNull
    @JoinColumn(name = "fk_pigs")
    @ManyToOne
    var pig: Pig = Pig(),
    var temperature: Double = 0.0,
    var heartRate: Int = 0,
    var respiratoryRate: Int = 0,
    var weight: Double = 0.0,
    var createdAt: LocalDateTime = LocalDateTime.now()
)



