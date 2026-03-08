package site.gutschi.quarkustest.restClient

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class BackendService(@param:RestClient val backendPort: BackendPort) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun backendData(): Array<BackendPort.BackendResult> {
        return backendPort.start()
    }
}
