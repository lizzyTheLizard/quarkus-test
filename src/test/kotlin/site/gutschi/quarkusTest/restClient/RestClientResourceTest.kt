package site.gutschi.quarkusTest.restClient

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.startsWith
import org.junit.jupiter.api.Test

@QuarkusTest
class RestClientResourceTest {
    @Test
    fun call() {
        given()
            .`when`().get("/rest-client")
            .then()
            .statusCode(200)
            .body(startsWith("[{\"id\":\"1\","))
    }

}