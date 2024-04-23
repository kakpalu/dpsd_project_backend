package org.cmu.pf_backend.service

import org.cmu.pf_backend.model.Pig
import org.cmu.pf_backend.repository.PigRepository
import org.springframework.stereotype.Service

@Service
class PigService (private val pigRepository: PigRepository) {
    
    fun addPig(pig: Pig): Pig {
        return pigRepository.save(pig)
    }

    fun getAllPigs(): List<Pig> {
        return pigRepository.findAll().toList()
    }

    fun getPig(id: Long): Pig {
        return pigRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
    }

    fun findByName(name : String): Pig? {
       val  variableList = pigRepository.findAll().toList()
         for (variable in variableList) {
              if (variable.name == name) {
                return variable
              }
         }
        return null
    }

    fun updatePig(id: Long, name: String, threshold: Double, email: String): Pig {
        val variable = pigRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
        variable.name = name
        return pigRepository.save(variable)
    }

    fun deletePig(id: Long) {
        if (!pigRepository.existsById(id)) throw IllegalArgumentException("Variable not found")
        pigRepository.deleteById(id)
    }
}