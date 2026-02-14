package fr.ftnl.tools.messageBuilder.core.dto.components.content

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class ContentComponentsTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testFileComponentSerialization() {
        val file = FileComponent(UnfurledMediaItem("https://example.com/file.png"))
            .setName("file.png")
            .setSpoiler(true)
        val encoded = json.encodeToString(file)

        assertTrue(encoded.contains("\"type\":13"))
        assertTrue(encoded.contains("\"url\":\"https://example.com/file.png\""))
        assertTrue(encoded.contains("\"name\":\"file.png\""))
        assertTrue(encoded.contains("\"spoiler\":true"))
    }

    @Test
    fun testThumbnailSerialization() {
        val thumbnail = Thumbnail(UnfurledMediaItem("https://example.com/thumb.png"))
            .setDescription("Thumb")
        val encoded = json.encodeToString(thumbnail)

        assertTrue(encoded.contains("\"type\":11"))
        assertTrue(encoded.contains("\"url\":\"https://example.com/thumb.png\""))
        assertTrue(encoded.contains("\"description\":\"Thumb\""))
    }

    @Test
    fun testMediaGallerySerialization() {
        val gallery = MediaGallery()
            .addItem(MediaGalleryItem(UnfurledMediaItem("https://example.com/a.png")))
        val encoded = json.encodeToString(gallery)

        assertTrue(encoded.contains("\"type\":12"))
        assertTrue(encoded.contains("\"items\":["))
        assertTrue(encoded.contains("\"url\":\"https://example.com/a.png\""))
    }

    @Test
    fun testUnfurledMediaItemSerialization() {
        val item = UnfurledMediaItem("https://example.com/media.png")
            .setHeight(64)
            .setWidth(128)
        val encoded = json.encodeToString(item)

        assertTrue(encoded.contains("\"url\":\"https://example.com/media.png\""))
        assertTrue(encoded.contains("\"height\":64"))
        assertTrue(encoded.contains("\"width\":128"))
    }
}

