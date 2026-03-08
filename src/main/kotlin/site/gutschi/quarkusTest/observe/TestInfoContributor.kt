package site.gutschi.quarkusTest.observe

import io.quarkus.info.runtime.spi.InfoContributor

//TODO: THis is not working. I have to figure out how to register this class in InfoRecorder
class TestInfoContributor : InfoContributor {
    override fun name(): String {
        return "test"
    }

    override fun data(): Map<String?, Any?> {
        return mapOf("key" to "value")
    }
}