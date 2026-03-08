package site.gutschi.quarkustest.observe

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness
import org.eclipse.microprofile.rest.client.inject.RestClient
import site.gutschi.quarkustest.restClient.BackendPort

@Liveness
@ApplicationScoped
class CustomHealthCheck(@param:RestClient private val backendPort: BackendPort) : HealthCheck {
    override fun call(): HealthCheckResponse {
        val response = HealthCheckResponse.named("CustomHealthCheck").withData("test.key", "value")
        try {
            backendPort.start()
            response.up()
        } catch (e: Exception) {
            response.down().withData("message", e.message)
        }
        return response.build()
    }
}
