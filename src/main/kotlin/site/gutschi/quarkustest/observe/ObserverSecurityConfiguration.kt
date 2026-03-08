package site.gutschi.quarkustest.observe

import io.quarkus.vertx.http.security.HttpSecurity
import jakarta.enterprise.event.Observes

//TODO: Prevent "is never used" warning
class ObserverSecurityConfiguration {
    fun configure(@Observes httpSecurity: HttpSecurity) {
        httpSecurity.path("/q/info", "/q/health/*", "/q/metrics")
            .basic()
            .authorization().roles("admin")
        httpSecurity.basic("QuarkusTestRealm")
    }
}
