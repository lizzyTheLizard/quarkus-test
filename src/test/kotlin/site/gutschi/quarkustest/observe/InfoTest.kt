package site.gutschi.quarkustest.observe

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class InfoTest {
    @Test
    fun unauthorized() {
        given()
            .`when`().get("/q/info")
            .then()
            .statusCode(401)
    }


    @Test
    fun authorized() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .get("/q/info")
            .then()
            .statusCode(200)
            .body("build.name", `is`("quarkus-test"))
    }

    @Test
    fun customInfo() {
        given()
            .auth().preemptive().basic("admin", "admin")
            .`when`().get("/q/info")
            .then()
            .statusCode(200)
            .body("test.key", `is`("value"))
    }

}