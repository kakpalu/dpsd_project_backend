package org.cmu.pf_backend.repository

import org.cmu.pf_backend.model.AmbientVariable
import org.springframework.data.repository.CrudRepository

interface AmbientVariableRepository : CrudRepository<AmbientVariable, Long>