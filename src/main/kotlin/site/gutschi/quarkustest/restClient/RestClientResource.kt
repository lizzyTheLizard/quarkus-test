package site.gutschi.quarkustest.restClient

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/rest-client")
class RestClientResource(val backendService: BackendService) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun call(): Array<BackendPort.BackendResult> {
        return backendService.backendData()
    }
}