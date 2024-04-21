package org.cmu.pf_backend.repository

import org.cmu.pf_backend.model.Farmer
import org.springframework.data.repository.CrudRepository

interface FarmerRepository : CrudRepository<Farmer, Long>