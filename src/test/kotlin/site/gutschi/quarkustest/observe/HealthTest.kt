package site.gutschi.quarkustest.observe

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class HealthTest {
    @Test
    fun unauthorized() {
        given()
            .`when`().get("/q/health")
            .then()
            .statusCode(401)
    }


    @Test
    fun authorized() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .get("/q/health")
            .then()
            .statusCode(200)
            .body("checks[1].status", `is`("UP"))
    }

    @Test
    fun customHealthCheck() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .`when`().get("/q/health")
            .then()
            .statusCode(200)
            .body("checks[0].name", `is`("CustomHealthCheck"))
    }
}