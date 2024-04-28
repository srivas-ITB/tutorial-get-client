package models

import kotlinx.serialization.Serializable

//Simula la Base de Dades
val customerStorage = mutableListOf<Customer>()

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)