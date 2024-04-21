package org.cmu.pf_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PfBackendApplication

fun main(args: Array<String>) {
    runApplication<PfBackendApplication>(*args)
}
