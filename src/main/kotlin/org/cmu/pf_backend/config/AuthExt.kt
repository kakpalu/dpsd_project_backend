package org.cmu.pf_backend.config
import org.cmu.pf_backend.model.Farmer
import org.springframework.security.core.Authentication
fun Authentication.toUser(): Farmer {
    return principal as Farmer
}