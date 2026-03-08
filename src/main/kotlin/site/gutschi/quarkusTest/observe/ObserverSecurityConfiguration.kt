package site.gutschi.quarkusTest.observe

import io.quarkus.vertx.http.security.HttpSecurity
import jakarta.enterprise.event.Observes

//TODO: Prevent "is never used" warning
class ObserverSecurityConfiguration {
    fun configure(@Observes httpSecurity: HttpSecurity) {
        httpSecurity.path("/q/info")
            .basic()
            .authorization().roles("admin")
        httpSecurity.basic("QuarkusTestRealm")
    }
}
