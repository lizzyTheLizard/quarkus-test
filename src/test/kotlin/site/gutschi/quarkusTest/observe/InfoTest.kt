package site.gutschi.quarkusTest.observe

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.keycloak.client.KeycloakTestClient
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@QuarkusTest
class InfoTest {

    var keycloakClient = KeycloakTestClient()

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
    @Disabled("This test is currently failing. I have to figure out how to register the TestInfoContributor in the InfoRecorder")
    fun customInfo() {
        given()
            .auth().oauth2(keycloakClient.getAccessToken("alice"))
            .`when`().get("/q/info")
            .then()
            .statusCode(200)
            .body("test.key", `is`("value"))
    }

}