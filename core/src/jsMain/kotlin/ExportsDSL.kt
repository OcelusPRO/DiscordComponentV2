@file:JsExport
@file:OptIn(ExperimentalJsExport::class, ExperimentalSerializationApi::class)

import fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.FileUpload
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromDynamic
import kotlinx.serialization.json.encodeToDynamic
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
class ComponentListBuilder {
    private val _components = mutableListOf<DiscordComponent>()
    
    val components: Array<DiscordComponent>
        get() = _components.toTypedArray()
    
    private fun <T : DiscordComponent> add(component: T): T {
        _components.add(component)
        return component
    }
    
    // --- LAYOUT (Imbrication) ---
    
    fun container(block: (ComponentListBuilder) -> Unit): Container {
        val c = Container()
        val childBuilder = ComponentListBuilder()
        block(childBuilder)
        childBuilder.components.forEach { c.addComponent(it) }
        return add(c)
    }
    
    fun actionRow(block: (ComponentListBuilder) -> Unit): ActionRow {
        val row = ActionRow()
        val childBuilder = ComponentListBuilder()
        block(childBuilder)
        childBuilder.components.forEach { row.addComponent(it) }
        return add(row)
    }
    
    fun section(accessory: DiscordComponent, block: (ComponentListBuilder) -> Unit): Section {
        val s = Section(accessory)
        val childBuilder = ComponentListBuilder()
        block(childBuilder)
        childBuilder.components.forEach { s.addComponent(it) }
        return add(s)
    }
    
    fun label(text: String, component: DiscordComponent): Label {
        return add(Label(text, component))
    }
    
    fun separator(): Separator {
        return add(Separator())
    }
    
    // --- INTERACTIVE ---
    
    fun button(style: Int, label: String? = null, customId: String? = null): Button {
        val b = Button(style).setLabel(label).setCustomId(customId)
        return add(b)
    }
    
    fun textInput(customId: String, style: Int, label: String? = null): TextInput {
        val i = TextInput(customId, style)
        if (label != null) i.setPlaceholder(label)
        return add(i)
    }
    
    fun stringSelect(customId: String, block: (StringSelect) -> Unit): StringSelect {
        val s = StringSelect(customId)
        block(s)
        return add(s)
    }
    
    fun fileUpload(customId: String): FileUpload {
        return add(FileUpload(customId))
    }
    
    // Entity Selects
    fun userSelect(customId: String) = add(UserSelect(customId))
    fun roleSelect(customId: String) = add(RoleSelect(customId))
    fun mentionableSelect(customId: String) = add(MentionableSelect(customId))
    fun channelSelect(customId: String) = add(ChannelSelect(customId))
    
    // --- CONTENT ---
    
    fun text(content: String): TextDisplay {
        return add(TextDisplay(content))
    }
    
    fun thumbnail(url: String): Thumbnail {
        return add(Thumbnail(UnfurledMediaItem(url)))
    }
    
    fun file(url: String, name: String? = null): FileComponent {
        val f = FileComponent(UnfurledMediaItem(url))
        if (name != null) f.setName(name)
        return add(f)
    }
    
    fun mediaGallery(block: (MediaGallery) -> Unit): MediaGallery {
        val g = MediaGallery()
        block(g)
        return add(g)
    }
}

private val jsonConfig = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

@JsExport
object EasyComponentsBuilder {
    /**
     * Point d'entrée principal pour construire une liste de composants en JS.
     */
    fun build(block: (ComponentListBuilder) -> Unit): Array<DiscordComponent> {
        val builder = ComponentListBuilder()
        block(builder)
        return builder.components
    }
    
    // Helpers pour les objets qui ne sont pas des composants mais des paramètres
    fun createOption(label: String, value: String) = SelectOption(label, value)
    fun createMedia(url: String) = UnfurledMediaItem(url)
    fun createEmoji() = DiscordEmoji()
    
    
    fun toJsonString(component: DiscordComponent, pretty: Boolean = false): String = component.toJsonString(pretty)
    fun toJsObject(component: DiscordComponent): dynamic = jsonConfig.encodeToDynamic(component)
    fun toJsArray(component: Array<DiscordComponent>): dynamic = jsonConfig.encodeToDynamic(component)
    
    /**
     * Charge des composants depuis une String JSON ou un objet JS.
     * @param input {String | Object | Array} L'élément à charger (String ou Object, Array ou Solo)
     * @returns {DiscordComponent | DiscordComponent[]} Un composant unique ou un tableau de composants.
     */
    fun fromJson(input: dynamic): dynamic {
        if (input == null) return null
        
        return try {
            val isString = js("typeof input === 'string'") as Boolean
            if (isString) {
                val jsonString = input as String
                val element = jsonConfig.parseToJsonElement(jsonString)
                if (element is JsonArray) jsonConfig.decodeFromString<Array<DiscordComponent>>(jsonString)
                else jsonConfig.decodeFromString<DiscordComponent>(jsonString)
            } else {
                val isArray = js("Array.isArray(input)") as Boolean
                if (isArray) jsonConfig.decodeFromDynamic<Array<DiscordComponent>>(input)
                else jsonConfig.decodeFromDynamic<DiscordComponent>(input)
            }
        } catch (e: Exception) {
            console.error("EasyComponentsBuilder.load error:", e.message)
            null
        }
    }
    
    fun fromJsonObject(input: dynamic): DiscordComponent {
        return fromJson(input) as DiscordComponent
    }
    
    /** Pour quand tu sais que c'est une liste */
    fun fromJsonArray(input: dynamic): Array<DiscordComponent> {
        return fromJson(input) as Array<DiscordComponent>
    }
    
    
}