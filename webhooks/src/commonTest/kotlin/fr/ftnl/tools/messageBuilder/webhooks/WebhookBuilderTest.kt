package fr.ftnl.tools.messageBuilder.webhooks

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class WebhookBuilderTest {

    @Test
    fun testFromUrl() {
        val builder = WebhookBuilder().fromUrl("https://discord.com/api/webhooks/123/token")
        assertEquals("https://discord.com/api/webhooks/123/token", builder.url)
        assertEquals("123", builder.id)
        assertEquals("token", builder.token)
    }

    @Test
    fun testFromIdAndToken() {
        val builder = WebhookBuilder().fromIdAndToken("123", "token")
        assertEquals("https://discord.com/api/webhooks/123/token", builder.url)
        assertEquals("123", builder.id)
        assertEquals("token", builder.token)
    }

    @Test
    fun testUrlParsing() {
        val builder = WebhookBuilder()
        builder.url = "https://discord.com/api/webhooks/999/xyz"
        assertEquals("999", builder.id)
        assertEquals("xyz", builder.token)
    }

    @Test
    fun testBuildUrlFailure() {
        val builder = WebhookBuilder()
        assertFailsWith<IllegalStateException> {
            builder.buildUrl()
        }
    }
}
