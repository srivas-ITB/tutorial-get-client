import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Customer

suspend fun main() {
    val client = HttpClient(CIO)

    var response: HttpResponse = client.post("http://127.0.0.1:8080/customer") {
        url("http://127.0.0.1:8080/customer")
        contentType(ContentType.Application.Json)
        setBody(Json.encodeToString(Customer("4", "John", "Doe", "john.doe@itb.cat")))
    }
    println("Status després del POST: ${response.status}")

    response = client.get("http://127.0.0.1:8080/customer")
    println("Status després del GET: ${response.status}")
    //println(response.bodyAsText())

    var customers = Json.decodeFromString<List<Customer>>(response.bodyAsText())
    println(customers)
    for (c in customers) {
        println("ID: ${c.id} - First Name: ${c.firstName} - LastName: ${c.lastName} - Email: ${c.email}")
    }

    client.close()
}