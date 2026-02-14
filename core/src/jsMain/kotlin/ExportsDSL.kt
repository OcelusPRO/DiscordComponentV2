@file:OptIn(ExperimentalJsExport::class, ExperimentalSerializationApi::class)

import fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGallery
import fr.ftnl.tools.messageBuilder.core.dto.components.content.MediaGalleryItem
import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import fr.ftnl.tools.messageBuilder.core.dto.components.content.Thumbnail
import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Button
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.Checkbox
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.FileUpload
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroup
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.SectionAccessoryComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromDynamic
import kotlinx.serialization.json.encodeToDynamic
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

private val jsonConfig = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

@JsExport
object JsDsl {
    fun message(block: (MessageDsl) -> Unit): Array<dynamic> {
        val dsl = MessageDsl()
        block(dsl)
        return dsl.build().unsafeCast<Array<dynamic>>()
    }
    
    fun modal(block: (ModalDsl) -> Unit): Array<dynamic> {
        val dsl = ModalDsl()
        block(dsl)
        return dsl.build().unsafeCast<Array<dynamic>>()
    }
    
    fun toJsonString(component: dynamic, pretty: Boolean = false): String {
        return requireComponent(component, "component").toJsonString(pretty)
    }
    
    fun toJsonStringArray(components: Array<dynamic>, pretty: Boolean = false): String {
        val config = if (pretty) Json { prettyPrint = true; encodeDefaults = true; ignoreUnknownKeys = true } else jsonConfig
        return config.encodeToString(toComponentArray(components))
    }
    
    fun toJsObject(component: dynamic): dynamic = jsonConfig.encodeToDynamic(requireComponent(component, "component"))
    fun toJsArray(components: Array<dynamic>): dynamic = jsonConfig.encodeToDynamic(toComponentArray(components))
    
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
            console.error("JsDsl.fromJson error:", e.message)
            null
        }
    }
}

private fun requireComponent(value: dynamic, name: String): DiscordComponent {
    return value as? DiscordComponent
        ?: throw IllegalArgumentException("Expected DiscordComponent for $name")
}

private fun toComponentArray(values: Array<dynamic>): Array<DiscordComponent> {
    return values.map { requireComponent(it, "components") }.toTypedArray()
}

@JsExport
class MessageDsl {
    private val components = mutableListOf<DiscordComponent>()
    
    internal fun build(): Array<DiscordComponent> = components.toTypedArray()
    
    private fun <T : DiscordComponent> add(component: T): T {
        components.add(component)
        return component
    }
    
    // Layout
    fun container(block: (ContainerDsl) -> Unit): Container {
        val dsl = ContainerDsl()
        block(dsl)
        return add(dsl.build())
    }
    
    fun actionRow(block: (ActionRowDsl) -> Unit): ActionRow {
        val dsl = ActionRowDsl()
        block(dsl)
        return add(dsl.build())
    }
    
    fun section(block: (SectionDsl) -> Unit): Section {
        val dsl = SectionDsl()
        block(dsl)
        return add(dsl.build())
    }
    
    fun separator(block: ((Separator) -> Unit)? = null): Separator {
        val s = Separator()
        block?.invoke(s)
        return add(s)
    }
    
    // Content
    fun text(content: String, block: ((TextDisplay) -> Unit)? = null): TextDisplay {
        val t = TextDisplay(content)
        block?.invoke(t)
        return add(t)
    }
    
    fun file(url: String, block: ((FileComponent) -> Unit)? = null): FileComponent {
        val f = FileComponent(UnfurledMediaItem(url))
        block?.invoke(f)
        return add(f)
    }
    
    fun thumbnail(url: String, block: ((Thumbnail) -> Unit)? = null): Thumbnail {
        val t = Thumbnail(UnfurledMediaItem(url))
        block?.invoke(t)
        return add(t)
    }
    
    fun mediaGallery(block: (MediaGalleryDsl) -> Unit): MediaGallery {
        val dsl = MediaGalleryDsl()
        block(dsl)
        return add(dsl.build())
    }
    
    // Interactive (message-compatible)
    fun button(customId: String, style: ButtonStyles, block: ((Button) -> Unit)? = null): Button {
        val b = Button(style).setCustomId(customId)
        block?.invoke(b)
        return add(b)
    }
    
    fun stringSelect(customId: String, block: ((StringSelect) -> Unit)? = null): StringSelect {
        val s = StringSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun userSelect(customId: String, block: ((UserSelect) -> Unit)? = null): UserSelect {
        val s = UserSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun roleSelect(customId: String, block: ((RoleSelect) -> Unit)? = null): RoleSelect {
        val s = RoleSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun mentionableSelect(customId: String, block: ((MentionableSelect) -> Unit)? = null): MentionableSelect {
        val s = MentionableSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun channelSelect(customId: String, block: ((ChannelSelect) -> Unit)? = null): ChannelSelect {
        val s = ChannelSelect(customId)
        block?.invoke(s)
        return add(s)
    }
}

@JsExport
class ContainerDsl {
    private val container = Container()
    
    var color: Int?
        get() = container.accentColor
        set(value) { container.accentColor = value }
    
    var spoiler: Boolean
        get() = container.spoiler
        set(value) { container.spoiler = value }
    
    fun actionRow(block: (ActionRowDsl) -> Unit): ActionRow {
        val dsl = ActionRowDsl()
        block(dsl)
        val row = dsl.build()
        container.addComponent(row)
        return row
    }
    
    fun section(block: (SectionDsl) -> Unit): Section {
        val dsl = SectionDsl()
        block(dsl)
        val section = dsl.build()
        container.addComponent(section)
        return section
    }
    
    fun separator(block: ((Separator) -> Unit)? = null): Separator {
        val s = Separator()
        block?.invoke(s)
        container.addComponent(s)
        return s
    }
    
    fun text(content: String, block: ((TextDisplay) -> Unit)? = null): TextDisplay {
        val t = TextDisplay(content)
        block?.invoke(t)
        container.addComponent(t)
        return t
    }
    
    fun mediaGallery(block: (MediaGalleryDsl) -> Unit): MediaGallery {
        val dsl = MediaGalleryDsl()
        block(dsl)
        val g = dsl.build()
        container.addComponent(g)
        return g
    }
    
    internal fun build(): Container = container
}

@JsExport
class ActionRowDsl {
    private val row = ActionRow()
    
    fun button(customId: String, style: ButtonStyles, block: ((Button) -> Unit)? = null): Button {
        val b = Button(style).setCustomId(customId)
        block?.invoke(b)
        row.addComponent(b)
        return b
    }
    
    fun stringSelect(customId: String, block: ((StringSelect) -> Unit)? = null): StringSelect {
        val s = StringSelect(customId)
        block?.invoke(s)
        row.addComponent(s)
        return s
    }
    
    fun userSelect(customId: String, block: ((UserSelect) -> Unit)? = null): UserSelect {
        val s = UserSelect(customId)
        block?.invoke(s)
        row.addComponent(s)
        return s
    }
    
    fun roleSelect(customId: String, block: ((RoleSelect) -> Unit)? = null): RoleSelect {
        val s = RoleSelect(customId)
        block?.invoke(s)
        row.addComponent(s)
        return s
    }
    
    fun mentionableSelect(customId: String, block: ((MentionableSelect) -> Unit)? = null): MentionableSelect {
        val s = MentionableSelect(customId)
        block?.invoke(s)
        row.addComponent(s)
        return s
    }
    
    fun channelSelect(customId: String, block: ((ChannelSelect) -> Unit)? = null): ChannelSelect {
        val s = ChannelSelect(customId)
        block?.invoke(s)
        row.addComponent(s)
        return s
    }
    
    internal fun build(): ActionRow = row
}

@JsExport
class SectionDsl {
    private val section = Section()
    
    fun text(content: String, block: ((TextDisplay) -> Unit)? = null): TextDisplay {
        val t = TextDisplay(content)
        block?.invoke(t)
        section.addComponent(t)
        return t
    }
    
    fun accessory(component: dynamic) {
        val accessory = component as? SectionAccessoryComponent
            ?: throw IllegalArgumentException("Expected SectionAccessoryComponent for accessory")
        section.setAccessory(accessory)
    }
    
    fun accessoryButton(customId: String, style: ButtonStyles, block: ((Button) -> Unit)? = null): Button {
        val b = Button(style).setCustomId(customId)
        block?.invoke(b)
        section.setAccessory(b)
        return b
    }
    
    fun accessoryThumbnail(url: String, block: ((Thumbnail) -> Unit)? = null): Thumbnail {
        val t = Thumbnail(UnfurledMediaItem(url))
        block?.invoke(t)
        section.setAccessory(t)
        return t
    }
    
    internal fun build(): Section = section
}

@JsExport
class MediaGalleryDsl {
    private val gallery = MediaGallery()
    
    fun item(url: String, block: ((MediaGalleryItem) -> Unit)? = null): MediaGalleryItem {
        val item = MediaGalleryItem(UnfurledMediaItem(url))
        block?.invoke(item)
        gallery.addItem(item)
        return item
    }
    
    internal fun build(): MediaGallery = gallery
}

@JsExport
class ModalDsl {
    private val components = mutableListOf<DiscordComponent>()
    
    internal fun build(): Array<DiscordComponent> = components.toTypedArray()
    
    private fun <T : DiscordComponent> add(component: T): T {
        components.add(component)
        return component
    }
    
    fun text(content: String, block: ((TextDisplay) -> Unit)? = null): TextDisplay {
        val t = TextDisplay(content)
        block?.invoke(t)
        return add(t)
    }
    
    fun checkbox(customId: String, block: ((Checkbox) -> Unit)? = null): Checkbox {
        val c = Checkbox(customId)
        block?.invoke(c)
        return add(c)
    }
    
    fun checkboxGroup(customId: String, block: ((CheckboxGroup) -> Unit)? = null): CheckboxGroup {
        val g = CheckboxGroup(customId)
        block?.invoke(g)
        return add(g)
    }
    
    fun radioGroup(customId: String, block: ((RadioGroup) -> Unit)? = null): RadioGroup {
        val g = RadioGroup(customId)
        block?.invoke(g)
        return add(g)
    }
    
    fun textInput(customId: String, style: Int, block: ((TextInput) -> Unit)? = null): TextInput {
        val i = TextInput(customId, style)
        block?.invoke(i)
        return add(i)
    }
    
    fun fileUpload(customId: String, block: ((FileUpload) -> Unit)? = null): FileUpload {
        val f = FileUpload(customId)
        block?.invoke(f)
        return add(f)
    }
    
    fun stringSelect(customId: String, block: ((StringSelect) -> Unit)? = null): StringSelect {
        val s = StringSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun userSelect(customId: String, block: ((UserSelect) -> Unit)? = null): UserSelect {
        val s = UserSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun roleSelect(customId: String, block: ((RoleSelect) -> Unit)? = null): RoleSelect {
        val s = RoleSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun mentionableSelect(customId: String, block: ((MentionableSelect) -> Unit)? = null): MentionableSelect {
        val s = MentionableSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun channelSelect(customId: String, block: ((ChannelSelect) -> Unit)? = null): ChannelSelect {
        val s = ChannelSelect(customId)
        block?.invoke(s)
        return add(s)
    }
    
    fun label(text: String, block: (LabelDsl) -> Unit): Label {
        val dsl = LabelDsl(text)
        block(dsl)
        return add(dsl.build())
    }
}

@JsExport
class LabelDsl(private val labelText: String) {
    var description: String? = null
    private var component: LabelChildComponent? = null
    
    private fun <T : LabelChildComponent> setComponent(component: T, block: ((T) -> Unit)?): T {
        block?.invoke(component)
        this.component = component
        return component
    }
    
    fun checkbox(customId: String, block: ((Checkbox) -> Unit)? = null): Checkbox =
        setComponent(Checkbox(customId), block)
    
    fun checkboxGroup(customId: String, block: ((CheckboxGroup) -> Unit)? = null): CheckboxGroup =
        setComponent(CheckboxGroup(customId), block)
    
    fun radioGroup(customId: String, block: ((RadioGroup) -> Unit)? = null): RadioGroup =
        setComponent(RadioGroup(customId), block)
    
    fun textInput(customId: String, style: Int, block: ((TextInput) -> Unit)? = null): TextInput =
        setComponent(TextInput(customId, style), block)
    
    fun fileUpload(customId: String, block: ((FileUpload) -> Unit)? = null): FileUpload =
        setComponent(FileUpload(customId), block)
    
    fun stringSelect(customId: String, block: ((StringSelect) -> Unit)? = null): StringSelect =
        setComponent(StringSelect(customId), block)
    
    fun userSelect(customId: String, block: ((UserSelect) -> Unit)? = null): UserSelect =
        setComponent(UserSelect(customId), block)
    
    fun roleSelect(customId: String, block: ((RoleSelect) -> Unit)? = null): RoleSelect =
        setComponent(RoleSelect(customId), block)
    
    fun mentionableSelect(customId: String, block: ((MentionableSelect) -> Unit)? = null): MentionableSelect =
        setComponent(MentionableSelect(customId), block)
    
    fun channelSelect(customId: String, block: ((ChannelSelect) -> Unit)? = null): ChannelSelect =
        setComponent(ChannelSelect(customId), block)
    
    internal fun build(): Label {
        val child = component ?: throw IllegalStateException("Label component is required")
        val label = Label(labelText, child)
        label.description = description
        return label
    }
}