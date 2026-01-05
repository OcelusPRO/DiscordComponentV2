package fr.ftnl.tools.messageBuilder.webhooks

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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

    suspend fun send(builder: WebhookBuilder, message: WebhookMessage) {
        val url = builder.buildUrl()

        // Merge builder defaults if message fields are missing
        val finalMessage = message.copy(
            username = message.username ?: builder.username,
            avatarUrl = message.avatarUrl ?: builder.avatarUrl
        )

        val response = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(finalMessage)
        }
        if (!response.status.isSuccess()) {
            throw WebhookException("Failed to send webhook: ${response.status} - ${response.bodyAsText()}")
        }
    }

    suspend fun send(builder: WebhookBuilder, components: List<DiscordComponent>) {
        send(builder, WebhookMessage(components = components))
    }
}

class WebhookException(message: String) : Exception(message)
