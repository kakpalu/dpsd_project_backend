package org.cmu.pf_backend.service

import org.cmu.pf_backend.model.AmbientVariable
import org.cmu.pf_backend.repository.AmbientVariableRepository
import org.springframework.stereotype.Service

@Service
class AmbientService(private val ambientRepository: AmbientVariableRepository) {

    fun addAmbientVariable(ambientVariable: AmbientVariable): AmbientVariable {
        return ambientRepository.save(ambientVariable)
    }

    fun getAllAmbientVariables(): List<AmbientVariable> {
        return ambientRepository.findAll().toList()
    }

    fun getVariable(id: Long): AmbientVariable {
        return ambientRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
    }

    fun findByName(name : String): AmbientVariable? {
       val  variableList = ambientRepository.findAll().toList()
         for (variable in variableList) {
              if (variable.name == name) {
                return variable
              }
         }

        return null
    }

    fun updateVariable(id: Long, name: String, threshold: Double, email: String): AmbientVariable {
        val variable = ambientRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
        variable.name = name
        variable.threshold = threshold
        return ambientRepository.save(variable)
    }

    fun deleteVariable(id: Long) {
        if (!ambientRepository.existsById(id)) throw IllegalArgumentException("Variable not found")
        ambientRepository.deleteById(id)
    }
}