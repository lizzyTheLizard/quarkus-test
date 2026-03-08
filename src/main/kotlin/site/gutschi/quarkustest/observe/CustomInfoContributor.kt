package site.gutschi.quarkustest.observe

import io.quarkus.info.runtime.spi.InfoContributor
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
class CustomInfoContributor : InfoContributor {
    override fun name(): String {
        return "test"
    }


    override fun data(): Map<String?, Any?> {
        return mapOf("key" to "value", "startuptime" to LocalDateTime.now().toString())
    }
}