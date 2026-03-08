package site.gutschi.quarkustest.sql

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class OtherEntity {
    @Id
    @GeneratedValue
    var id: Long = 0

    lateinit var name: String
}
