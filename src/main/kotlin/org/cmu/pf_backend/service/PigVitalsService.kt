package org.cmu.pf_backend.service

import com.google.gson.JsonParser
import org.cmu.pf_backend.model.PigVitals
import org.cmu.pf_backend.repository.PigVitalsRepository
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class PigVitalsService(private val pigVitalsRepository: PigVitalsRepository) {

    fun addPigVitals(pigVitals: PigVitals): PigVitals {
        return pigVitalsRepository.save(pigVitals)
    }

    fun getAllPigVitals(): List<PigVitals> {
        return pigVitalsRepository.findAll().toList()
    }

    fun getPigVitals(id: Long): PigVitals {
        return pigVitalsRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
    }

    fun updatePigVitals(
        id: Long,
        tempreture: Double,
        heartRate: Int,
        respiratoryRate: Int,
        weight: Double,
        email: String
    ): PigVitals {
        val vitalsID = pigVitalsRepository.findById(id).orElseThrow { IllegalArgumentException("Variable not found") }
        return pigVitalsRepository.save(
            vitalsID.copy(
                temperature = tempreture,
                heartRate = heartRate,
                respiratoryRate = respiratoryRate,
                weight = weight
            )
        )
    }

    fun deletePig(id: Long) {
        if (!pigVitalsRepository.existsById(id)) throw IllegalArgumentException("Variable not found")
        pigVitalsRepository.deleteById(id)
    }

    fun getPigDisease(symptoms: String): String {
        val baseURl = "https://api.hy-tech.my.id/api/gemini/"
        val questionSubText = "What is the disease of a pig with symptoms "
        val httpReqeust = HttpRequest.newBuilder()
            .uri(URI.create((baseURl + questionSubText + symptoms).replace(" ", "%20")))
            .GET()
            .build()
        val response = HttpClient.newHttpClient().send(httpReqeust, HttpResponse.BodyHandlers.ofString())
        val answer = JsonParser.parseString(response.body()).asJsonObject
        println(answer)
        return if (answer.has("text")) answer.get("text").asString else "No disease found"
    }
}