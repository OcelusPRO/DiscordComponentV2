@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

import fr.ftnl.tools.messageBuilder.core.dto.components.content.*
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.*
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.*
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
object EasyComponentsV2 {
    
    // Layout
    fun createContainer() = Container()
    fun createActionRow() = ActionRow()
    fun createSection(accessory: DiscordComponent) = Section(accessory)
    fun createSeparator() = Separator()
    fun createLabel(label: String, component: DiscordComponent) = Label(label, component)
    
    // Interactive
    fun createButton(style: Int) = Button(style)
    fun createTextInput(customId: String, style: Int) = TextInput(customId, style)
    fun createStringSelect(customId: String) = StringSelect(customId)
    fun createSelectOption(label: String, value: String) = SelectOption(label, value)
    fun createFileUpload(customId: String) = FileUpload(customId)
    
    // Entity Selects
    fun createUserSelect(customId: String) = UserSelect(customId)
    fun createRoleSelect(customId: String) = RoleSelect(customId)
    fun createMentionableSelect(customId: String) = MentionableSelect(customId)
    fun createChannelSelect(customId: String) = ChannelSelect(customId)
    fun createSelectDefaultValue(id: String, type: String) = SelectDefaultValue(id, type)
    
    // Content
    fun createTextDisplay(content: String) = TextDisplay(content)
    fun createThumbnail(media: UnfurledMediaItem) = Thumbnail(media)
    fun createFileComponent(media: UnfurledMediaItem) = FileComponent(media)
    fun createMediaGallery() = MediaGallery()
    fun createMediaGalleryItem(media: UnfurledMediaItem) = MediaGalleryItem(media)
    fun createUnfurledMediaItem(url: String) = UnfurledMediaItem(url)
    
    // Utils
    fun createEmoji() = DiscordEmoji()
    
    // Accès aux constantes de style
    val ButtonStyles = fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ButtonStyles
}