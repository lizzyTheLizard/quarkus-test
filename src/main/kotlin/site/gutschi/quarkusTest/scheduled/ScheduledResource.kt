package site.gutschi.quarkusTest.scheduled

import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/scheduled")
class ScheduledResource(private val scheduledBean: ScheduledBean) {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun getCounter(): String {
        return scheduledBean.get().toString()
    }

    @POST
    @Path("/reset")
    fun reset() {
        return scheduledBean.reset()
    }
}