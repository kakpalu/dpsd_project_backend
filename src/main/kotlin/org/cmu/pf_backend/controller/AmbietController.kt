package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.AddAmbientVariableDto
import org.cmu.pf_backend.dto.AmbientVariableResponseDto
import org.cmu.pf_backend.model.AmbientVariable
import org.cmu.pf_backend.service.AmbientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class AmbietController (val ambientService: AmbientService) {

    @GetMapping("/ambient_variables")
    fun getAmbientVariables(): ResponseEntity<List<AmbientVariableResponseDto>>{

      return ResponseEntity(ambientService.getAllAmbientVariables().map {
          AmbientVariableResponseDto(
              id = it.id,
              name = it.name,
              threshold = it.threshold 
          )
      }, HttpStatus.OK)
    }


    @PostMapping("/ambient_variables")
    fun addAmbientVariable(@RequestBody addAmbientVariable: AddAmbientVariableDto): ResponseEntity<List<AmbientVariableResponseDto>> {

        val ambientVariable = AmbientVariable(
            name = addAmbientVariable.name,
            threshold = addAmbientVariable.threshold
        )
      ambientService.addAmbientVariable(ambientVariable)

        return ResponseEntity(ambientService.getAllAmbientVariables().map {
            AmbientVariableResponseDto(
                id = it.id,
                name = it.name,
                threshold = it.threshold
            )
        }, HttpStatus.OK)
    }
}