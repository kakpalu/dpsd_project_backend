package org.cmu.pf_backend.controller

import org.cmu.pf_backend.dto.AddAmbientVariableDto
import org.cmu.pf_backend.dto.AddPigDto
import org.cmu.pf_backend.dto.AmbientVariableResponseDto
import org.cmu.pf_backend.dto.PigDto
import org.cmu.pf_backend.model.AmbientVariable
import org.cmu.pf_backend.model.Pig
import org.cmu.pf_backend.service.PigService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PigController (val pigService: PigService) {
    @GetMapping("/pigs")
    fun getPigs(): ResponseEntity<List<PigDto>> {
        return ResponseEntity(pigService.getAllPigs().map {
            PigDto(
                id = it.id,
                name = it.name,
                weight = it.weight,
                age = it.age,
                breed = it.breed,
            )
        }, HttpStatus.OK)
    }

    @PostMapping("/pigs")
    fun addPig(@RequestBody addPigDto: AddPigDto): ResponseEntity<List<PigDto>> {

        val pig = Pig(
            name = addPigDto.name,
            weight = addPigDto.weight,
            age = addPigDto.age,
            breed = addPigDto.breed,
        )
        pigService.addPig(pig)

        return ResponseEntity(pigService.getAllPigs().map {
            PigDto(
                id = it.id,
                name = it.name,
                weight = it.weight,
                age = it.age,
                breed = it.breed,
            )
        }, HttpStatus.OK)
    }
}