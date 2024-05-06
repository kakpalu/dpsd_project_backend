package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.AddPigVitalsDto
import org.cmu.pf_backend.dto.PigVitalsResponseDto
import org.cmu.pf_backend.model.PigVitals
import org.cmu.pf_backend.service.PigService
import org.cmu.pf_backend.service.PigVitalsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PigVitalsController(val pigVialsService: PigVitalsService, val pigService: PigService) {

    @GetMapping("/pig_vitals")
    fun getPigVitals(): ResponseEntity<List<PigVitalsResponseDto>> {

        return ResponseEntity(pigVialsService.getAllPigVitals().map {
            PigVitalsResponseDto(
                pigId = it.pig.id,
                temperature = it.temperature,
                heartRate = it.heartRate,
                respiratoryRate = it.respiratoryRate,
                weight = it.weight
            )
        }, HttpStatus.OK)
    }


    @PostMapping("/pig_vitals")
    fun addPigVitials(@RequestBody addPigVitals: AddPigVitalsDto): ResponseEntity<List<PigVitalsResponseDto>> {

        //check if pig id exists
        if (!pigService.existsById(addPigVitals.pigId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val pigVitals = PigVitals(
            pig = pigService.getPig(addPigVitals.pigId),
            temperature = addPigVitals.temperature,
            heartRate = addPigVitals.heartRate,
            respiratoryRate = addPigVitals.respiratoryRate,
            weight = addPigVitals.weight
        )
        pigVialsService.addPigVitals(pigVitals)
        return ResponseEntity(pigVialsService.getAllPigVitals().map {
            PigVitalsResponseDto(
                pigId = it.pig.id,
                temperature = it.temperature,
                heartRate = it.heartRate,
                respiratoryRate = it.respiratoryRate,
                weight = it.weight
            )
        }, HttpStatus.OK)
    }
}