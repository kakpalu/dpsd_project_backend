package org.cmu.pf_backend.service

import org.cmu.pf_backend.model.Farmer
import org.cmu.pf_backend.repository.FarmerRepository
import org.springframework.stereotype.Service
import org.cmu.pf_backend.service.HashService

@Service
class FarmerService(private val farmerRepository: FarmerRepository) {

    private val hashService = HashService()

    fun createUser(farmer: Farmer): Farmer {
        //check if a famer with the same email already exists
        val userList = farmerRepository.findAll().toList()
        for (user in userList) {
            if (user.email == farmer.email) {
                throw IllegalArgumentException("User with the same email already exists")
            }
        }
        return farmerRepository.save(farmer)
    }

    fun getAllUsers(): List<Farmer> {
        return farmerRepository.findAll().toList()
    }

    fun getUser(id: Long): Farmer {
        return farmerRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
    }

    fun findByToken(token: String): Farmer? {
        val  userList = farmerRepository.findAll().toList()
        for (user in userList) {
            if (user.token == token) {
                return user
            }
        }
        return null
    }

    fun findByEmail(email: String): Farmer? {
       val  userList = farmerRepository.findAll().toList()
         for (user in userList) {
              if (user.email == email) {
                return user
              }
         }
        return null
    }

    fun updateUser(id: Long, firstName: String, lastName: String, email: String): Farmer {
        val user = farmerRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
        user.firstName = firstName
        user.lastName = lastName
        user.email = email
        return farmerRepository.save(user)
    }

fun deleteUser(id: Long) {
        if (!farmerRepository.existsById(id)) throw IllegalArgumentException("User not found")
        farmerRepository.deleteById(id)
    }

    fun changePassword(id: Long, password: String): Farmer {

        val user = farmerRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
        user.password = hashService.hashBcrypt(password)
        return farmerRepository.save(user)
    }

    // function to reset password
    fun resetPassword(email: String, password: String): Farmer {
        val user = findByEmail(email) ?: throw IllegalArgumentException("User not found")
        user.password = hashService.hashBcrypt(password)
        return farmerRepository.save(user)
    }

       fun storeToken(token: String, farmer: Farmer) {
            // Store the token in the database
           farmer.token = "Bearer $token"
            farmerRepository.save(farmer)
        }
}