package site.gutschi.quarkusTest.sql

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/sql")
class SqlResource(val entityRepository: OtherRepository) {

    @GET
    @Path("active-record")
    @Produces(MediaType.APPLICATION_JSON)
    fun listActiveRecord(@QueryParam(value = "name") name: String): List<ActiveRecord> {
        return ActiveRecord.findAllByName(name)
    }

    @GET
    @Path("active-record/starts-with")
    @Produces(MediaType.APPLICATION_JSON)
    fun listActiveRecordStartsWith(@QueryParam(value = "name") name: String): List<ActiveRecord> {
        return ActiveRecord.findAllByNameStartWith(name)
    }

    @POST
    @Path("active-record")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun addActiveRecord(input: ActiveRecord): ActiveRecord {
        input.persist()
        return input
    }


    @POST
    @Path("active-record/delete")
    @Transactional
    fun deleteActiveRecord() {
        ActiveRecord.deleteAll()
    }

    @GET
    @Path("repository")
    @Produces(MediaType.APPLICATION_JSON)
    fun listRepository(@QueryParam(value = "name") name: String): List<OtherEntity> {
        return entityRepository.findAllByName(name)
    }

    @GET
    @Path("repository/starts-with")
    @Produces(MediaType.APPLICATION_JSON)
    fun listRepositoryStartsWith(@QueryParam(value = "name") name: String): List<OtherEntity> {
        return entityRepository.findAllByNameStartWith(name)
    }

    @POST
    @Path("repository")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun addOtherEntity(input: OtherEntity): OtherEntity {
        entityRepository.persist(input)
        return input
    }

    @POST
    @Path("repository/delete")
    @Transactional
    fun deleteOtherEntity() {
        entityRepository.deleteAll()
    }

}