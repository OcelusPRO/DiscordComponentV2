package fr.ftnl.tools.messageBuilder.webhooks

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WebhookClientTest {

    @Test
    fun testSendWebhookSuccess() = runTest {
        val mockEngine = MockEngine { request ->
            assertEquals(HttpMethod.Post, request.method)
            assertEquals("application/json", request.body.contentType.toString())

            val body = request.body.toByteArray().decodeToString()
            // TextDisplay serializes to content
            assertTrue(body.contains("\"content\": \"Hello World\"") || body.contains("\"content\":\"Hello World\""), "Body should contain content 'Hello World': $body")
            // Depending on serialization formatting (pretty print), spaces might vary.
            assertTrue(body.contains("\"username\": \"BotName\"") || body.contains("\"username\":\"BotName\""), "Body should contain 'BotName': $body")

            respond(
                content = io.ktor.utils.io.ByteReadChannel(""),
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
        val builder = WebhookBuilder().fromUrl("https://discord.com/api/webhooks/123/abc")
        builder.username = "BotName"

        val components = listOf(TextDisplay(content = "Hello World"))

        webhookClient.send(builder, components)
    }

    @Test
    fun testSendWebhookFailure() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = io.ktor.utils.io.ByteReadChannel("Bad Request"),
                status = HttpStatusCode.BadRequest,
                headers = headersOf(HttpHeaders.ContentType, "text/plain")
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
        val builder = WebhookBuilder().fromUrl("https://discord.com/api/webhooks/123/abc")
        val components = emptyList<DiscordComponent>()

        assertFailsWith<WebhookException> {
            webhookClient.send(builder, components)
        }
    }
}
