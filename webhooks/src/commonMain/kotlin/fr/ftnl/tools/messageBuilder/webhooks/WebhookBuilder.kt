package fr.ftnl.tools.messageBuilder.webhooks

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class WebhookBuilder {
    var url: String = ""
        set(value) {
            field = value
            parseUrl(value)
        }

    var id: String = ""
    var token: String = ""
    var username: String? = null
    var avatarUrl: String? = null

    fun fromUrl(url: String): WebhookBuilder {
        this.url = url
        return this
    }

    fun fromIdAndToken(id: String, token: String): WebhookBuilder {
        this.id = id
        this.token = token
        this.url = "https://discord.com/api/webhooks/$id/$token"
        return this
    }

    private fun parseUrl(url: String) {
        val regex = Regex("https://discord.com/api/webhooks/(\\d+)/([\\w-]+)")
        val match = regex.find(url)
        if (match != null) {
            this.id = match.groupValues[1]
            this.token = match.groupValues[2]
        }
    }

    fun buildUrl(): String {
        if (url.isNotEmpty()) return url
        if (id.isNotEmpty() && token.isNotEmpty()) {
            return "https://discord.com/api/webhooks/$id/$token"
        }
        throw IllegalStateException("Webhook URL or ID/Token must be set.")
    }
}
