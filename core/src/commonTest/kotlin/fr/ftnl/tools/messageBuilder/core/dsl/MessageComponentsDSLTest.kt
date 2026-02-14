package fr.ftnl.tools.messageBuilder.core.dsl

import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.messageComponents
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.SpacingType
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MessageComponentsDSLTest {
    private val json = Json { encodeDefaults = true }
    
    @Test
    fun testMessageComponentsDSL() {
        val components = messageComponents {
            container {
                color = 0xFFFFFF
                spoiler = true
                text {
                    content = "Container text"
                }
                section {
                    text {
                        content = "Section text"
                    }
                    
                    accessory {
                        button("btn_accessory_id", ButtonStyles.LINK) {
                            url = "https://example.com"
                        }
                    }
                }
                section {
                    text {
                        content = "Second section"
                    }
                    accessory {
                        thumbnail(media = UnfurledMediaItem("https://example.com/thumb.png"))
                    }
                }
                
                separator {
                    spacing = SpacingType.LARGE
                    divider = false
                }
            }
            actionRow {
                button("btn_primary", ButtonStyles.PRIMARY) {
                    label = "OK"
                    disabled = true
                }
                stringSelect("select") {
                    placeholder = "Pick"
                    minValues = 1
                    maxValues = 2
                    required = false
                    option("Option A", "a") {
                        description = "desc"
                        emoji = DiscordEmoji().setName("smile")
                        default = true
                    }
                }
                userSelect("user") {
                    placeholder = "User"
                    defaultValues = listOf(SelectDefaultValue("123", "user"))
                }
                roleSelect("role") {
                    placeholder = "Role"
                }
                mentionableSelect("mention") {
                    placeholder = "Mention"
                }
                channelSelect("channel") {
                    channelTypes = listOf(0)
                    placeholder = "Channel"
                }
            }
            file("https://example.com/file.png") {}
            thumbnail("https://example.com/thumb2.png") {}
            mediaGallery {
                item("https://example.com/a.png") {
                    description = "A"
                    spoiler = true
                }
            }
            textDisplay {
                content = "Root text"
            }
            separator {
                spacing = SpacingType.SMALL
            }
        }
        assertNotNull(components)
        
        val encoded = json.encodeToString(components)
        assertTrue(encoded.contains("\"type\":17"))
        assertTrue(encoded.contains("\"type\":9"))
        assertTrue(encoded.contains("\"type\":10"))
        assertTrue(encoded.contains("\"type\":2"))
        assertTrue(encoded.contains("\"type\":3"))
        assertTrue(encoded.contains("\"type\":5"))
        assertTrue(encoded.contains("\"type\":6"))
        assertTrue(encoded.contains("\"type\":7"))
        assertTrue(encoded.contains("\"type\":8"))
        assertTrue(encoded.contains("\"type\":11"))
        assertTrue(encoded.contains("\"type\":12"))
        assertTrue(encoded.contains("\"type\":13"))
        assertTrue(encoded.contains("\"type\":14"))
    }
}