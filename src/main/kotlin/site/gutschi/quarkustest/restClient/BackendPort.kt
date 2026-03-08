package site.gutschi.quarkustest.restClient

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(baseUri = "https://jsonplaceholder.typicode.com", configKey = "backend-api")
fun interface BackendPort {
    @GET
    @Path("/todos")
    fun start(): Array<BackendResult>

    data class BackendResult(val userId: Int, val id: Int, val title: String)
}