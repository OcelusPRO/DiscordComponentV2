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
        var content: String? = null
        val finalComponents = mutableListOf<DiscordComponent>()

        components.forEach { component ->
            when (component.type) {
                10 -> { // TextDisplay
                    // We assume it has a 'content' property. Casting depends on visibility,
                    // but since we don't have direct access to TextDisplay class internals easily via casting in general common code
                    // without knowing exact type, we rely on the fact that if it's passed here it's likely our DTO.
                    // However, type 10 in our core is TextDisplay.
                    // Since we can't easily cast to specific implementation without dependency coupling or strict checks,
                    // We might need to rely on `toString` or specific interface if available.
                    // Actually, we imported TextDisplay in test, but here in commonMain we depend on core.
                    // Let's assume we can cast if we import it.
                    // But wait, TextDisplay is in core.

                    // Simple approach: Check if it's TextDisplay by type or class and extract content.
                    // Since we don't want to enforce specific classes too hard if possible, but core is a dependency.
                    // Let's try to find a way to extract text.
                    // For now, let's assume standard TextDisplay behavior from core.
                    // We need to cast to something that has content.
                    // Looking at core interfaces, SectionChildComponent etc don't enforce 'content'.
                    // We might need to inspect the object or change strategy.

                    // Given the context of the user request and existing code:
                    // TextDisplay (Type 10) -> content
                    // Others -> components

                    // Reflection is limited in KMP.
                    // Let's assume the user passes data that we can process.
                    // If we can't access 'content' property easily, we might need a helper or assume it's valid.
                    // But wait, we have `project(":core")` dependency. We can import `TextDisplay`.
                }
            }
        }

        // Revised approach:
        // Filter TextDisplay components for 'content'.
        // Filter everything else into 'components'.
        // Note: Discord expects ActionRows (Type 1) for top-level components.
        // If we have Buttons (Type 2) or Selects (Type 3/5/6/7/8) at top level, we must wrap them in ActionRow.

        val textComponents = components.filter { it.type == 10 }
        if (textComponents.isNotEmpty()) {
            content = textComponents.joinToString("\n") {
                // We need to access content. We can try casting to dynamic or using a known interface if available.
                // Since TextDisplay is a data class in core, we can cast if we import it.
                (it as? fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay)?.content ?: ""
            }
        }

        val otherComponents = components.filter { it.type != 10 }
        val processedComponents = mutableListOf<DiscordComponent>()

        var currentActionRowBuffer = mutableListOf<DiscordComponent>()

        otherComponents.forEach { comp ->
            if (comp.type == 1) {
                // If it's already an ActionRow, flush buffer and add it
                if (currentActionRowBuffer.isNotEmpty()) {
                    processedComponents.add(fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = currentActionRowBuffer.toList()))
                    currentActionRowBuffer.clear()
                }
                processedComponents.add(comp)
            } else {
                // It's a button or select or other interactive component.
                // We should add it to a buffer.
                // Note: Select Menus (Type 3+) usually take up a full row.
                // Buttons (Type 2) can share a row (up to 5).
                // Simplification: If it's a Select, flush buffer, add Select wrapped in Row, continue.
                // If it's a Button, add to buffer.

                if (comp.type == 2) { // Button
                    if (currentActionRowBuffer.size >= 5) {
                        processedComponents.add(fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = currentActionRowBuffer.toList()))
                        currentActionRowBuffer.clear()
                    }
                    currentActionRowBuffer.add(comp)
                } else {
                    // Assuming Select Menu or other valid row-occupying component
                     if (currentActionRowBuffer.isNotEmpty()) {
                        processedComponents.add(fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = currentActionRowBuffer.toList()))
                        currentActionRowBuffer.clear()
                    }
                    // Wrap this single component in a row
                    processedComponents.add(fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = listOf(comp)))
                }
            }
        }

        if (currentActionRowBuffer.isNotEmpty()) {
            processedComponents.add(fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow(components = currentActionRowBuffer.toList()))
        }

        send(builder, WebhookMessage(content = content, components = processedComponents))
    }
}

class WebhookException(message: String) : Exception(message)
