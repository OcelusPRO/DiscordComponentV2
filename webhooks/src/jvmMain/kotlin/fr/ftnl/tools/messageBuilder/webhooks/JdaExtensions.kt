package fr.ftnl.tools.messageBuilder.webhooks

import net.dv8tion.jda.api.entities.Webhook

fun Webhook.toBuilder(): WebhookBuilder {
    val builder = WebhookBuilder()
    builder.fromUrl(this.url)
    // JDA Webhook might not have token exposed directly if fetched without authentication?
    // Usually url is enough.
    // We can also set default name/avatar if available
    builder.username = this.name
    // JDA Webhook entity doesn't have getAvatarUrl directly, it might be on getDefaultUser() or we construct it.
    // However, looking at the decompiled interface, there is no getAvatarUrl on Webhook interface.
    // But there is getDefaultUser().
    builder.avatarUrl = this.defaultUser.avatarUrl
    return builder
}
