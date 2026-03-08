package site.gutschi.quarkusTest.sql

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class SqlResourceTest {
    @Test
    fun activeRecord() {
        given()
            .contentType("application/json")
            .`when`().post("/sql/active-record/delete")
            .then()
            .statusCode(204)

        given()
            .`when`().get("/sql/active-record?name=Test")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .contentType("application/json")
            .body("""{"name":"Test"}""")
            .`when`().post("/sql/active-record")
            .then()
            .statusCode(200)
            .body(`is`("""{"id":1,"name":"Test"}"""))

        given()
            .`when`().get("/sql/active-record?name=Test")
            .then()
            .statusCode(200)
            .body(`is`("""[{"id":1,"name":"Test"}]"""))

        given()
            .`when`().get("/sql/active-record?name=Te")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .`when`().get("/sql/active-record/starts-with?name=Te")
            .then()
            .statusCode(200)
            .body(`is`("""[{"id":1,"name":"Test"}]"""))
    }


    @Test
    fun repository() {
        given()
            .contentType("application/json")
            .`when`().post("/sql/repository/delete")
            .then()
            .statusCode(204)

        given()
            .`when`().get("/sql/repository?name=Test")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .contentType("application/json")
            .body("""{"name":"Test"}""")
            .`when`().post("/sql/repository")
            .then()
            .statusCode(200)
            .body(`is`("""{"id":1,"name":"Test"}"""))

        given()
            .`when`().get("/sql/repository?name=Test")
            .then()
            .statusCode(200)
            .body(`is`("""[{"id":1,"name":"Test"}]"""))

        given()
            .`when`().get("/sql/repository?name=Te")
            .then()
            .statusCode(200)
            .body(`is`("[]"))

        given()
            .`when`().get("/sql/repository/starts-with?name=Te")
            .then()
            .statusCode(200)
            .body(`is`("""[{"id":1,"name":"Test"}]"""))
    }
}