package site.gutschi.quarkustest.oidc

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.keycloak.client.KeycloakTestClient
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class OidcResourceTest {

    var keycloakClient = KeycloakTestClient()

    @Test
    fun unauthorized() {
        given()
            .`when`().get("/oidc/user")
            .then()
            .statusCode(200)
            .body(`is`("Unknown"))
    }


    @Test
    fun authorized() {
        given()
            .auth().oauth2(keycloakClient.getAccessToken("alice"))
            .`when`().get("/oidc/user")
            .then()
            .statusCode(200)
            .body(`is`("alice"))
    }

    @Test
    fun unauthorizedAdmin() {
        given()
            .`when`().get("/oidc/admin")
            .then()
            .statusCode(401)
    }

    @Test
    fun wrongUserAdmin() {
        given()
            .auth().oauth2(keycloakClient.getAccessToken("bob"))
            .`when`().get("/oidc/admin")
            .then()
            .statusCode(403)
    }


    @Test
    fun authorizedAdmin() {
        given()
            .auth().oauth2(keycloakClient.getAccessToken("alice"))
            .`when`().get("/oidc/admin")
            .then()
            .statusCode(200)
            .body(`is`("Hello Admin"))
    }
}