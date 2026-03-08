package site.gutschi.quarkusTest.restClient

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(baseUri = "http://api.restful-api.dev", configKey = "backend-api")
fun interface BackendPort {
    @GET
    @Path("/objects")
    fun start(): Array<BackendResult>

    data class BackendResult(val id: String, val name: String)
}
