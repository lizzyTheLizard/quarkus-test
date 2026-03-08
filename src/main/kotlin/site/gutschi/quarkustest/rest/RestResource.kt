package site.gutschi.quarkustest.rest

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/rest")
class RestResource {
    @GET
    @Path("string")
    @Produces(MediaType.TEXT_PLAIN)
    final fun string(): String {
        return "Hello"
    }

    @GET
    @Path("params/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun pathParam(name: String, @QueryParam("q") q: String?): String {
        return "Path parameter was $name, and query parameter was ${q ?: "empty"}"
    }

    @POST
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun json(request: Input): Output {
        return Output("Hello ${request.name}, you are ${request.age} years old")
    }

    data class Input(val name: String, @param:JsonProperty(required = true) val age: Int)

    data class Output(val message: String)
}