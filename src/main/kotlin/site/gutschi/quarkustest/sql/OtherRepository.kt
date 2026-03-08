package site.gutschi.quarkustest.sql

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class OtherRepository : PanacheRepository<OtherEntity> {
    fun findAllByName(name: String) = find("name", name).list()
    fun findAllByNameStartWith(name: String) = find("name LIKE ?1", "$name%").list()

}