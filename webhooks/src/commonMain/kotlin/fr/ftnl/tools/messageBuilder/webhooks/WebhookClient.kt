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
        val content = components.filterIsInstance<TextDisplay>()
            .joinToString("\n") { it.content }
            .takeIf { it.isNotEmpty() }

        val processedComponents = mutableListOf<DiscordComponent>()
        val currentActionRowBuffer = mutableListOf<DiscordComponent>()

        components.filter { it !is TextDisplay }.forEach { comp ->
            if (comp is ActionRow) {
                flushBuffer(processedComponents, currentActionRowBuffer)
                processedComponents.add(comp)
            } else {
                // Button (Type 2) can share a row (up to 5).
                // Other types (Selects) typically take a full row.
                if (comp.type == 2) {
                    if (currentActionRowBuffer.size >= 5) {
                        flushBuffer(processedComponents, currentActionRowBuffer)
                    }
                    currentActionRowBuffer.add(comp)
                } else {
                    flushBuffer(processedComponents, currentActionRowBuffer)
                    processedComponents.add(ActionRow(components = listOf(comp)))
                }
            }
        }
        flushBuffer(processedComponents, currentActionRowBuffer)

        send(builder, WebhookMessage(content = content, components = processedComponents))
    }

    private fun flushBuffer(target: MutableList<DiscordComponent>, buffer: MutableList<DiscordComponent>) {
        if (buffer.isNotEmpty()) {
            target.add(ActionRow(components = buffer.toList()))
            buffer.clear()
        }
    }
}

class WebhookException(message: String) : Exception(message)
