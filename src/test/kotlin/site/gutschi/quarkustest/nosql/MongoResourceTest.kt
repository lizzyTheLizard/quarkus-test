package site.gutschi.quarkustest.nosql

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class MongoResourceTest {
    @Test
    fun repository() {
        given()
            .contentType("application/json")
            .`when`().post("/mongo/delete")
            .then()
            .statusCode(204)

        given()
            .`when`().get("/mongo?name=Test")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .contentType("application/json")
            .body("""{"name":"Test"}""")
            .`when`().post("/mongo")
            .then()
            .statusCode(200)
            .body(endsWith(""""name":"Test"}"""))

        given()
            .`when`().get("/mongo?name=Test")
            .then()
            .statusCode(200)
            .body(endsWith(""""name":"Test"}]"""))

        given()
            .`when`().get("/mongo?name=Te")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .`when`().get("/mongo/starts-with?name=Te")
            .then()
            .statusCode(200)
            .body(endsWith(""""name":"Test"}]"""))
    }
}