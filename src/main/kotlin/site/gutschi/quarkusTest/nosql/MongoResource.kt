package site.gutschi.quarkusTest.nosql

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/mongo")
class MongoResource(val mongoRepository: MongoRepository) {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listRepository(@QueryParam(value = "name") name: String): List<Document> {
        return mongoRepository.findAllByName(name)
    }

    @GET
    @Path("starts-with")
    @Produces(MediaType.APPLICATION_JSON)
    fun listRepositoryStartsWith(@QueryParam(value = "name") name: String): List<Document> {
        return mongoRepository.findAllByNameStartWith(name)
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun addOtherEntity(input: Document): Document {
        mongoRepository.persist(input)
        return input
    }

    @POST
    @Path("delete")
    @Transactional
    fun delete() {
        mongoRepository.deleteAll()
    }
}