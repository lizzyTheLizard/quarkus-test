package site.gutschi.quarkusTest.observe

import io.quarkus.security.identity.AuthenticationRequestContext
import io.quarkus.security.identity.IdentityProvider
import io.quarkus.security.identity.SecurityIdentity
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest
import io.quarkus.security.runtime.QuarkusPrincipal
import io.quarkus.security.runtime.QuarkusSecurityIdentity
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class BasicIdentityProvider : IdentityProvider<UsernamePasswordAuthenticationRequest> {
    override fun getRequestType(): Class<UsernamePasswordAuthenticationRequest> {
        return UsernamePasswordAuthenticationRequest::class.java
    }

    override fun authenticate(
        p0: UsernamePasswordAuthenticationRequest?,
        p1: AuthenticationRequestContext?
    ): Uni<SecurityIdentity> {
        if (p0 === null)
            return Uni.createFrom().failure(IllegalArgumentException("Authentication request is null"))
        if (!p0.username.equals("admin") || !p0.password.password.concatToString().equals("admin"))
            return Uni.createFrom().failure(IllegalArgumentException("Invalid credentials"))
        val principal = QuarkusPrincipal(p0.username)
        val identity = QuarkusSecurityIdentity.builder().setPrincipal(principal).addRole("admin").build()
        return Uni.createFrom().item(identity)
    }
}