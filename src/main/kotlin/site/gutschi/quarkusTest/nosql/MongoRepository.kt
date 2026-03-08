package site.gutschi.quarkusTest.nosql

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class MongoRepository : PanacheMongoRepository<Document> {
    fun findAllByName(name: String) = find("name", name).list()
    fun findAllByNameStartWith(name: String) = find("name LIKE ?1", "/$name/").list()
}