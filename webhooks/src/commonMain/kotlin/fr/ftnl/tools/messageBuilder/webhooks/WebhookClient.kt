package fr.ftnl.tools.messageBuilder.webhooks

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class WebhookClient(private val httpClient: HttpClient) {

    constructor() : this(HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }
    })

    suspend fun send(webhookUrl: String, components: List<DiscordComponent>) {
        val payload = WebhookPayload(components)
        val response = httpClient.post(webhookUrl) {
            contentType(ContentType.Application.Json)
            setBody(payload)
        }
        if (!response.status.isSuccess()) {
            throw WebhookException("Failed to send webhook: ${response.status} - ${response.bodyAsText()}")
        }
    }
}

@Serializable
private data class WebhookPayload(
    val components: List<DiscordComponent>
)

class WebhookException(message: String) : Exception(message)
