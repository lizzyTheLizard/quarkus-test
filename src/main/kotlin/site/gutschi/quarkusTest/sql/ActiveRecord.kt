package site.gutschi.quarkusTest.sql

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity

@Entity
class ActiveRecord : PanacheEntity() {
    companion object : PanacheCompanion<ActiveRecord> {
        fun findAllByName(name: String) = find("name", name).list()
        fun findAllByNameStartWith(name: String) = find("name LIKE ?1", "$name%").list()
    }

    lateinit var name: String
}