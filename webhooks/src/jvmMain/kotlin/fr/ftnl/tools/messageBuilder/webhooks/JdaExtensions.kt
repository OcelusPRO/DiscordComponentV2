package fr.ftnl.tools.messageBuilder.webhooks

import net.dv8tion.jda.api.entities.Webhook

fun Webhook.toBuilder(): WebhookBuilder {
    val builder = WebhookBuilder()
    builder.fromUrl(this.url)
    // JDA Webhook might not have token exposed directly if fetched without authentication?
    // Usually url is enough.
    // We can also set default name/avatar if available
    builder.username = this.name
    builder.avatarUrl = this.defaultUser.avatarUrl

    return builder
}
