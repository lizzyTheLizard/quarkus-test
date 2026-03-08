package site.gutschi.quarkustest.observe

import jakarta.annotation.PostConstruct
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import io.quarkus.logging.Log

@Path("/log")
class LogResource {

    @PostConstruct
    fun init() {
        println("Log initialized 2X")
        Log.info("Log initialized")
    }

    @GET
    fun log(): String {
        Log.info("LogResource called")
        return "Logged"
    }
}
