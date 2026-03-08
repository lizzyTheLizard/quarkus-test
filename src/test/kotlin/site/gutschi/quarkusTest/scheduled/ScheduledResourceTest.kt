package site.gutschi.quarkusTest.scheduled

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@QuarkusTest
class ScheduledResourceTest {
    @Test
    fun scheduled() {
        val initialValue = given()
            .`when`().get("/scheduled")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()


        Thread.sleep(1000)
        val newValue = given()
            .`when`().get("/scheduled")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        assertTrue(initialValue < newValue, "Counter has not been incremented (initial: $initialValue, new: $newValue)")
    }
}