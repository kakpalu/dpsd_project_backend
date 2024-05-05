package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.GetDiseaseDto
import org.cmu.pf_backend.service.PigVitalsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PigDiseaseController(val pigVitalsService: PigVitalsService) {

    @PostMapping("/disease")
    fun getPigDisease(@RequestBody getPigDisease: GetDiseaseDto): ResponseEntity<String> {
        val disease = pigVitalsService.getPigDisease(getPigDisease.symptoms)
        return ResponseEntity(disease, HttpStatus.OK)
    }
}