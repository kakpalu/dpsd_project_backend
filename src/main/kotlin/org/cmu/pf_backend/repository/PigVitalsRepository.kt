package org.cmu.pf_backend.repository

import org.cmu.pf_backend.model.PigVitals
import org.springframework.data.repository.CrudRepository

interface PigVitalsRepository : CrudRepository<PigVitals, Long>