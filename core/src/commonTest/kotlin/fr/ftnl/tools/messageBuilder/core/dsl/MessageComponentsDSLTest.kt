package fr.ftnl.tools.messageBuilder.core.dsl

import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.messageComponents
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.SpacingType
import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class MessageComponentsDSLTest {
    
    @Test
    fun testMessageComponentsDSL() {
        val components = messageComponents {
            container {
                color = 0xFFFFFF
                spoiler = true
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
                
                separator {
                    spacing = SpacingType.LARGE
                    divider = false
                }
            }

        }
        assertNotNull(components)
        
        components.forEach { println(Json.encodeToString(ComponentSerializer, it)) }
    }
}