package site.gutschi.quarkusTest.oidc

import jakarta.annotation.security.RolesAllowed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.SecurityContext

@Path("/oidc")
class OidcResource {

    @GET
    @Path("user")
    @Produces(MediaType.TEXT_PLAIN)
    final fun hello(@Context securityContext: SecurityContext): String {
        return securityContext.userPrincipal?.name ?: "Unknown"
    }

    @GET
    @Path("admin")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    final fun adminHello(): String {
        return "Hello Admin"
    }
}