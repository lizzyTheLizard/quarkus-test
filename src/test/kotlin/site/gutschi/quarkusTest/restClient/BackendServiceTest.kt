package site.gutschi.quarkusTest.restClient

import io.quarkus.test.InjectMock
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`


@QuarkusTest
class BackendServiceTest {
    @InjectMock
    @RestClient
    lateinit var backendPort: BackendPort

    @Inject
    lateinit var backendService: BackendService

    @Test
    fun backendData() {
        `when`(backendPort.start()).thenReturn(arrayOf(BackendPort.BackendResult(1, 1, "Test")))
        val data = backendService.backendData()
        assertNotNull(data)
        assertTrue(data.isNotEmpty())
        assertEquals(1, data[0].id)
        assertEquals("Test", data[0].title)
    }
}