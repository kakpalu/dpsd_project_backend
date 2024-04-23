package org.cmu.pf_backend.repository

import org.cmu.pf_backend.model.Pig
import org.springframework.data.repository.CrudRepository

interface PigRepository : CrudRepository<Pig, Long>