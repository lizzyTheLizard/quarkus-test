package site.gutschi.quarkusTest.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class RestResourceTest {
    @Test
    fun hello() {
        given()
            .`when`().get("/rest/string")
            .then()
            .statusCode(200)
            .body(`is`("Hello"))
    }

    @Test
    fun params() {
        given()
            .`when`().get("/rest/params/Test?q=Query")
            .then()
            .statusCode(200)
            .body(`is`("Path parameter was Test, and query parameter was Query"))
    }

    @Test
    fun json() {
        given()
            .contentType("application/json")
            .body("""{"name": "Test", "age": 30}""")
            .`when`().post("/rest/json")
            .then()
            .statusCode(200)
            .body(`is`("{\"message\":\"Hello Test, you are 30 years old\"}"))
    }

    @Test
    fun jsonInvalidInput() {
        val invalidInput = "{\"name\": \"Test"
        given()
            .contentType("application/json")
            .body(invalidInput)
            .`when`().post("/rest/json")
            .then()
            .statusCode(400)
    }

    @Test
    fun jsonEmptyInput() {
        given()
            .contentType("application/json")
            .body("""{"test": "Test"}""")
            .`when`().post("/rest/json")
            .then()
            .statusCode(400)
    }

    @Test
    fun jsonMissingInput() {
        given()
            .`when`().post("/rest/json")
            .then()
            .statusCode(415)
    }

}