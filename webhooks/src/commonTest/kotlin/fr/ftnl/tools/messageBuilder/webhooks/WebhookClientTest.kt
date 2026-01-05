package fr.ftnl.tools.messageBuilder.webhooks

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WebhookClientTest {

    @Test
    fun testSendWebhook() = runTest {
        val mockEngine = MockEngine { request ->
            assertEquals(HttpMethod.Post, request.method)
            assertEquals("application/json", request.body.contentType.toString())

            // Verify body content loosely or exactly
            val body = request.body.toByteArray().decodeToString()
            // We expect the JSON representation of the container
            // Since Container is likely complex, we can just check if it's not empty for now or check for specific fields
            assertTrue(body.isNotEmpty())

            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.NoContent,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                })
            }
        }

        val webhookClient = WebhookClient(client)
        val container = Container(
            id = 1,
            components = emptyList(),
            accentColor = 0xFFFFFF,
            spoiler = false
        )

        webhookClient.send("https://discord.com/api/webhooks/123/abc", container)
    }
}
