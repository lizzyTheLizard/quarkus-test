package site.gutschi.quarkusTest.nosql

import io.quarkus.mongodb.panache.common.MongoEntity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.bson.types.ObjectId

@MongoEntity(collection = "documents", database = "test")
class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: ObjectId? = null
    lateinit var name: String
}